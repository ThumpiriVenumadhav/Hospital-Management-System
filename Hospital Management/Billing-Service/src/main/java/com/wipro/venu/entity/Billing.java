package com.wipro.venu.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private Long medicalRecordId;

    private double amount;

    private boolean paid;

    private LocalDateTime createdAt = LocalDateTime.now();
}
