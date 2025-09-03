package com.wipro.venu.service.impl;

import com.wipro.venu.entity.Billing;
import com.wipro.venu.repository.BillingRepository;
import com.wipro.venu.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final BillingRepository repository;

    @Override
    public Billing generateBill(Long patientId, Long medicalRecordId, double amount) {
        Billing bill = new Billing();
        bill.setPatientId(patientId);
        bill.setMedicalRecordId(medicalRecordId);
        bill.setAmount(amount);
        bill.setPaid(false);
        return repository.save(bill);
    }

    @Override
    public List<Billing> getBillsByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    @Override
    public Billing markAsPaid(Long billId) {
        Billing bill = repository.findById(billId).orElseThrow();
        bill.setPaid(true);
        return repository.save(bill);
    }
}
