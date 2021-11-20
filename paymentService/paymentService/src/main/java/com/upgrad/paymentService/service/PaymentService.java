package com.upgrad.paymentService.service;

import com.upgrad.paymentService.dto.PaymentDTO;
import com.upgrad.paymentService.entity.TransactionDetailsEntity;

public interface PaymentService{

    public TransactionDetailsEntity acceptTransaction(PaymentDTO paymentDTO);

    public TransactionDetailsEntity getTransaction(int transactionId);

}
