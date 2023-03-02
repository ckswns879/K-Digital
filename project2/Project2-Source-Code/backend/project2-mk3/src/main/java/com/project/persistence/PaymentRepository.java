package com.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
