package com.upgrad.paymentService.service;

import com.upgrad.paymentService.dao.PaymentDAO;
import com.upgrad.paymentService.dto.PaymentDTO;
import com.upgrad.paymentService.entity.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
   private PaymentDAO paymentDAO;

    @Override
    public TransactionDetailsEntity acceptTransaction(PaymentDTO paymentDTO) {

        TransactionDetailsEntity newTransaction = new TransactionDetailsEntity();
newTransaction.setPaymentMode(paymentDTO.getPaymentMode());
newTransaction.setBookingId(paymentDTO.getBookingId());
newTransaction.setCardNumber(paymentDTO.getCardNumber());
newTransaction.setUpiId(paymentDTO.getUpiId());

return paymentDAO.save(newTransaction);

    }

    @Override
    public TransactionDetailsEntity getTransaction(int transactionId) {
        Optional<TransactionDetailsEntity> transactionDetailsEntity = paymentDAO.findTransactionDetailsEntityByTransactionId(transactionId);
        return transactionDetailsEntity.get();
    }


}
