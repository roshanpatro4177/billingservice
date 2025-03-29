package com.cox.billingservice.kafka;

import com.cox.billingservice.model.Billing;
import com.cox.billingservice.service.BillingService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class InventoryEventListener {
    private final BillingService billingService;

    public InventoryEventListener(BillingService billingService) {
        this.billingService = billingService;
    }

    @KafkaListener(topics = "inventory-topic", groupId = "billing-service-group")
    public void listenInventoryEvent(Map<String, Object> eventData) {
        String orderId = (String) eventData.get("orderId");
        String resourceId = (String) eventData.get("resourceId");

        Billing billing = new Billing();
        billing.setOrderId(orderId);
        billing.setResourceId(resourceId);
        billing.setAmount(100.00);  // Placeholder pricing logic
        billing.setTimestamp(LocalDateTime.now());

        billingService.generateBill(billing);
    }
}