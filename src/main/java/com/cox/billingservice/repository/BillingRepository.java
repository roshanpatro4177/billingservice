package com.cox.billingservice.repository;

import com.cox.billingservice.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingRepository extends JpaRepository<Billing, Long> {
    Optional<Billing> findByOrderId(String orderId);
}
