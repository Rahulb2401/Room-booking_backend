package com.upgrad.paymentService.controller;

import com.upgrad.paymentService.dto.PaymentDTO;
import com.upgrad.paymentService.entity.TransactionDetailsEntity;
import com.upgrad.paymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/payment")
public class PaymentsController {

@Autowired
    private PaymentService paymentService;

    @PostMapping(value="/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity paymentConfirmation(@RequestBody PaymentDTO paymentDTO){

        TransactionDetailsEntity savedTransaction = paymentService.acceptTransaction(paymentDTO);
        return new ResponseEntity(savedTransaction.getTransactionId(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/transaction/{transactionId}")
    public ResponseEntity getTransaction(@PathVariable(name="transactionId") int transactionId){
        TransactionDetailsEntity transactionDetails = paymentService.getTransaction(transactionId);

        return new ResponseEntity(transactionDetails, HttpStatus.OK);
    }
}
