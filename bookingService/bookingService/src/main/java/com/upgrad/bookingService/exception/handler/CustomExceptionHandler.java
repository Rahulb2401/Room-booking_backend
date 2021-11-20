package com.upgrad.bookingService.exception.handler;

import com.upgrad.bookingService.dto.ExceptionResponse;
import com.upgrad.bookingService.exception.InvalidBookingId;
import com.upgrad.bookingService.exception.InvalidPaymentMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

@ExceptionHandler(InvalidPaymentMode.class)
    public final ResponseEntity<ExceptionResponse> handlePaymentException(InvalidPaymentMode e, WebRequest request){

    ExceptionResponse exceptionResponse =new ExceptionResponse();
exceptionResponse.setMessage("Invalid Mode of Payment");
exceptionResponse.setStatusCode(400);
return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(InvalidBookingId.class)
    public final ResponseEntity<ExceptionResponse> handleBookingException(InvalidBookingId e, WebRequest request){

    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setMessage("Invalid Booking ID");
    exceptionResponse.setStatusCode(400);

    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
}


}
