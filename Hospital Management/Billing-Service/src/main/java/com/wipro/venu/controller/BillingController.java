package com.wipro.venu.controller;

import com.wipro.venu.entity.Billing;
import com.wipro.venu.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;

    // Generate a new bill
    @PostMapping
    public ResponseEntity<Billing> generateBill(
            @RequestParam Long patientId,
            @RequestParam Long medicalRecordId,
            @RequestParam double amount) {
        return ResponseEntity.ok(billingService.generateBill(patientId, medicalRecordId, amount));
    }

    // Get all bills of a patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Billing>> getBillsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(billingService.getBillsByPatient(patientId));
    }

    // Mark bill as paid
    @PutMapping("/{billId}/pay")
    public ResponseEntity<Billing> markBillAsPaid(@PathVariable Long billId) {
        return ResponseEntity.ok(billingService.markAsPaid(billId));
    }
}
