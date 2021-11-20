package com.upgrad.bookingService.exception;

public class InvalidBookingId extends RuntimeException{

private int STATUS_CODE;


public InvalidBookingId(String message, int statusCode){
    super(message);
    this.STATUS_CODE =statusCode;
}
}
