package com.upgrad.paymentService.dao;

import com.upgrad.paymentService.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentDAO extends JpaRepository<TransactionDetailsEntity, Integer> {

    Optional<TransactionDetailsEntity> findTransactionDetailsEntityByTransactionId(int id);
}
