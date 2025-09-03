package com.wipro.venu.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.venu.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find appointments by patient
    List<Appointment> findByPatientId(Long patientId);

    // Find appointments by doctor
    List<Appointment> findByDoctorId(Long doctorId);
}
