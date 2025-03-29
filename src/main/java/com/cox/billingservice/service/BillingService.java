package com.cox.billingservice.service;

import com.cox.billingservice.kafka.BillingProducer;
import com.cox.billingservice.model.Billing;
import com.cox.billingservice.repository.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingService {
    private final BillingRepository billingRepository;
    private final BillingProducer billingProducer;

    public BillingService(BillingRepository billingRepository, BillingProducer billingProducer) {
        this.billingRepository = billingRepository;
        this.billingProducer = billingProducer;
    }

    public Optional<Billing> getBillingDetails(String orderId) {
        return billingRepository.findByOrderId(orderId);
    }

    public Billing generateBill(Billing billing) {
        Billing savedBilling = billingRepository.save(billing);
        billingProducer.sendBillingEvent(savedBilling);
        return savedBilling;
    }
}
