package com.wipro.venu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wipro.venu.Repository.AppointmentRepository;
import com.wipro.venu.dto.AppointmentRequest;
import com.wipro.venu.dto.AppointmentResponse;
import com.wipro.venu.entity.Appointment;
import com.wipro.venu.enums.AppointmentStatus;
import com.wipro.venu.exceptions.AppointmentNotFoundException;
import com.wipro.venu.kafka.AppointmentEventProducer;
import com.wipro.venu.service.AppointmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentEventProducer eventProducer;

    @Override
    public AppointmentResponse bookAppointment(AppointmentRequest request) {
        log.info("Booking appointment for patientId: {} with doctorId: {}", request.getPatientId(), request.getDoctorId());

        Appointment appointment = Appointment.builder()
                .patientId(request.getPatientId())
                .doctorId(request.getDoctorId())
                .appointmentDate(request.getAppointmentDate())
                .status(AppointmentStatus.BOOKED)
                .build();

        Appointment saved = appointmentRepository.save(appointment);

        log.info("Appointment booked successfully with ID: {}", saved.getId());

        // publish event for Notification Service
        eventProducer.sendAppointmentBookedEvent(saved);
        log.info("Appointment booked event published for appointmentId: {}", saved.getId());

        return mapToResponse(saved);
    }

    @Override
    public AppointmentResponse getAppointmentById(Long id) {
        log.info("Fetching appointment with ID: {}", id);

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Appointment not found with ID: {}", id);
                    return new AppointmentNotFoundException("Appointment not found with id " + id);
                });

        log.info("Appointment found with ID: {}", appointment.getId());
        return mapToResponse(appointment);
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByPatient(Long patientId) {
        log.info("Fetching appointments for patientId: {}", patientId);

        List<AppointmentResponse> appointments = appointmentRepository.findByPatientId(patientId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        log.info("Total appointments found for patientId {}: {}", patientId, appointments.size());
        return appointments;
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByDoctor(Long doctorId) {
        log.info("Fetching appointments for doctorId: {}", doctorId);

        List<AppointmentResponse> appointments = appointmentRepository.findByDoctorId(doctorId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        log.info("Total appointments found for doctorId {}: {}", doctorId, appointments.size());
        return appointments;
    }

    @Override
    public AppointmentResponse updateAppointment(Long id, AppointmentRequest request) {
        log.info("Updating appointment with ID: {}", id);

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Appointment not found with ID: {}", id);
                    return new AppointmentNotFoundException("Appointment not found with id " + id);
                });

        appointment.setDoctorId(request.getDoctorId());
        appointment.setPatientId(request.getPatientId());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus(request.getStatus());

        Appointment updated = appointmentRepository.save(appointment);

        log.info("Appointment updated successfully with ID: {}", updated.getId());
        return mapToResponse(updated);
    }

    @Override
    public void cancelAppointment(Long id) {
        log.info("Cancelling appointment with ID: {}", id);

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Appointment not found with ID: {}", id);
                    return new AppointmentNotFoundException("Appointment not found with id " + id);
                });

        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);

        log.info("Appointment cancelled successfully with ID: {}", id);

        // publish cancel event
        eventProducer.sendAppointmentCancelledEvent(appointment);
        log.info("Appointment cancelled event published for appointmentId: {}", id);
    }

    private AppointmentResponse mapToResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .appointmentDate(appointment.getAppointmentDate())
                .status(appointment.getStatus())
                .build();
    }
}
