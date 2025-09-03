package com.wipro.venu.service;

import com.wipro.venu.entity.Billing;
import java.util.List;

public interface BillingService {
    Billing generateBill(Long patientId, Long medicalRecordId, double amount);
    List<Billing> getBillsByPatient(Long patientId);
    Billing markAsPaid(Long billId);
}
