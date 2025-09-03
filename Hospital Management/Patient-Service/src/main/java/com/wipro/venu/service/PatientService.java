package com.wipro.venu.service;

import java.util.List;

import com.wipro.venu.dto.PatientRequest;
import com.wipro.venu.dto.PatientResponse;

public interface PatientService {

    PatientResponse createPatient(PatientRequest request);

    PatientResponse getPatientById(Long id);

    List<PatientResponse> getAllPatients();

    PatientResponse updatePatient(Long id, PatientRequest request);

    void deletePatient(Long id);
}
