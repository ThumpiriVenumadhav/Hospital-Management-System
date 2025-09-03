package com.wipro.venu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wipro.venu.dto.PatientRequest;
import com.wipro.venu.dto.PatientResponse;
import com.wipro.venu.entity.Patient;
import com.wipro.venu.exceptions.PatientNotFoundException;
import com.wipro.venu.repository.PatientRepository;
import com.wipro.venu.service.PatientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public PatientResponse createPatient(PatientRequest request) {
        log.info("Creating patient with name: {}", request.getName());

        Patient patient = Patient.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .age(request.getAge())
                .gender(request.getGender())
                .address(request.getAddress())
                .medicalHistory(request.getMedicalHistory())
                .build();

        Patient saved = patientRepository.save(patient);

        log.info("Patient created successfully with ID: {}", saved.getId());
        return mapToResponse(saved);
    }

    @Override
    public PatientResponse getPatientById(Long id) {
        log.info("Fetching patient with ID: {}", id);

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Patient not found with id: {}", id);
                    return new PatientNotFoundException("Patient not found with id: " + id);
                });

        log.info("Patient found with ID: {}", patient.getId());
        return mapToResponse(patient);
    }

    @Override
    public List<PatientResponse> getAllPatients() {
        log.info("Fetching all patients");
        List<PatientResponse> patients = patientRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        log.info("Total patients found: {}", patients.size());
        return patients;
    }

    @Override
    public PatientResponse updatePatient(Long id, PatientRequest request) {
        log.info("Updating patient with ID: {}", id);

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Patient not found with id: {}", id);
                    return new PatientNotFoundException("Patient not found with id: " + id);
                });

        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setPhone(request.getPhone());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setAddress(request.getAddress());
        patient.setMedicalHistory(request.getMedicalHistory());

        Patient updated = patientRepository.save(patient);

        log.info("Patient updated successfully with ID: {}", updated.getId());
        return mapToResponse(updated);
    }

    @Override
    public void deletePatient(Long id) {
        log.info("Deleting patient with ID: {}", id);

        if (!patientRepository.existsById(id)) {
            log.error("Cannot delete, patient not found with ID: {}", id);
            throw new PatientNotFoundException("Patient not found with id: " + id);
        }

        patientRepository.deleteById(id);
        log.info("Patient deleted successfully with ID: {}", id);
    }

    private PatientResponse mapToResponse(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .age(patient.getAge())
                .gender(patient.getGender())
                .address(patient.getAddress())
                .medicalHistory(patient.getMedicalHistory())
                .build();
    }
}
