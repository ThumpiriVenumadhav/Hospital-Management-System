package com.wipro.venu.entity;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.wipro.venu.enums.AppointmentStatus;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference to Patient (just storing ID, since PatientService is separate microservice)
    @Column(nullable = false)
    private Long patientId;

    // Reference to Doctor
    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    private String notes; // Reason for visit / additional info
}
