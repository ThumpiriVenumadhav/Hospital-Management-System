package com.wipro.venu.service;


import java.util.List;

import com.wipro.venu.dto.DoctorRequest;
import com.wipro.venu.dto.DoctorResponse;



public interface DoctorService {
    DoctorResponse createDoctor(DoctorRequest request);
    DoctorResponse getDoctorById(Long id);
    List<DoctorResponse> getAllDoctors();
    DoctorResponse updateDoctor(Long id, DoctorRequest request);
    void deleteDoctor(Long id);
}

