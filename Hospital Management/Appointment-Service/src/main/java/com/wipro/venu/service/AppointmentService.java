package com.wipro.venu.service;


import java.util.List;

import com.wipro.venu.dto.AppointmentRequest;
import com.wipro.venu.dto.AppointmentResponse;

public interface AppointmentService {

    AppointmentResponse bookAppointment(AppointmentRequest request);

    AppointmentResponse getAppointmentById(Long id);

    List<AppointmentResponse> getAppointmentsByPatient(Long patientId);

    List<AppointmentResponse> getAppointmentsByDoctor(Long doctorId);

    AppointmentResponse updateAppointment(Long id, AppointmentRequest request);

    void cancelAppointment(Long id);
}

