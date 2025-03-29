package com.cox.billingservice.controller;

import com.cox.billingservice.model.Billing;
import com.cox.billingservice.service.BillingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/billing")
public class BillingController {
    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Billing> getBillingDetails(@PathVariable String orderId) {
        Optional<Billing> billing = billingService.getBillingDetails(orderId);
        return billing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/generate")
    public ResponseEntity<Billing> generateBill(@RequestBody Billing billing) {
        Billing savedBilling = billingService.generateBill(billing);
        return ResponseEntity.ok(savedBilling);
    }
}