package com.compter.copter.repos;

import com.compter.copter.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
