package com.upgrad.bookingService.exception;

public class InvalidPaymentMode extends RuntimeException{

private int STATUS_CODE;

public InvalidPaymentMode(String message, int statusCode){
    super(message);
    this.STATUS_CODE = statusCode;
}
}
