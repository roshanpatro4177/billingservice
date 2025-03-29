package com.cox.billingservice.kafka;

import com.cox.billingservice.model.Billing;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BillingProducer {

    private final KafkaTemplate<String, Billing> kafkaTemplate;

    public BillingProducer(KafkaTemplate<String, Billing> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBillingEvent(Billing billing) {
        kafkaTemplate.send("billing-topic", billing.getOrderId(), billing);
    }
}
