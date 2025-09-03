package com.wipro.venu.dto;



import java.time.LocalDateTime;

import com.wipro.venu.enums.AppointmentStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequest {

    @NotNull(message = "Patient Id is required")
    private Long patientId;

    @NotNull(message = "Doctor Id is required")
    private Long doctorId;

    @NotNull(message = "Appointment date is required")
    @Future(message = "Appointment date must be in future")
    private LocalDateTime appointmentDate;

    private AppointmentStatus status; // Optional, default set in service
}
