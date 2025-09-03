package com.wipro.venu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wipro.venu.Repository.DoctorRepository;
import com.wipro.venu.dto.DoctorRequest;
import com.wipro.venu.dto.DoctorResponse;
import com.wipro.venu.entity.Doctor;
import com.wipro.venu.exceptions.DoctorNotFoundException;
import com.wipro.venu.service.DoctorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public DoctorResponse createDoctor(DoctorRequest request) {
        log.info("Creating doctor with name: {}", request.getName());

        Doctor doctor = Doctor.builder()
                .name(request.getName())
                .specialization(request.getSpecialization())
                .email(request.getEmail())
                .phone(request.getPhone())
                .department(request.getDepartment())
                .experience(request.getExperience())
                .gender(request.getGender())
                .build();

        Doctor savedDoctor = doctorRepository.save(doctor);

        log.info("Doctor created successfully with ID: {}", savedDoctor.getId());
        return mapToResponse(savedDoctor);
    }

    @Override
    public DoctorResponse getDoctorById(Long id) {
        log.info("Fetching doctor with ID: {}", id);

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Doctor not found with ID: {}", id);
                    return new DoctorNotFoundException("Doctor not found with id: " + id);
                });

        log.info("Doctor found with ID: {}", doctor.getId());
        return mapToResponse(doctor);
    }

    @Override
    public List<DoctorResponse> getAllDoctors() {
        log.info("Fetching all doctors");

        List<DoctorResponse> doctors = doctorRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        log.info("Total doctors found: {}", doctors.size());
        return doctors;
    }

    @Override
    public DoctorResponse updateDoctor(Long id, DoctorRequest request) {
        log.info("Updating doctor with ID: {}", id);

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Doctor not found with ID: {}", id);
                    return new DoctorNotFoundException("Doctor not found with id: " + id);
                });

        doctor.setName(request.getName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setEmail(request.getEmail());
        doctor.setPhone(request.getPhone());
        doctor.setDepartment(request.getDepartment());
        doctor.setExperience(request.getExperience());
        doctor.setGender(request.getGender());

        Doctor updatedDoctor = doctorRepository.save(doctor);

        log.info("Doctor updated successfully with ID: {}", updatedDoctor.getId());
        return mapToResponse(updatedDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        log.info("Deleting doctor with ID: {}", id);

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Doctor not found with ID: {}", id);
                    return new DoctorNotFoundException("Doctor not found with id: " + id);
                });

        doctorRepository.delete(doctor);
        log.info("Doctor deleted successfully with ID: {}", id);
    }

    private DoctorResponse mapToResponse(Doctor doctor) {
        return DoctorResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .specialization(doctor.getSpecialization())
                .email(doctor.getEmail())
                .phone(doctor.getPhone())
                .department(doctor.getDepartment())
                .experience(doctor.getExperience())
                .gender(doctor.getGender())
                .build();
    }
}
