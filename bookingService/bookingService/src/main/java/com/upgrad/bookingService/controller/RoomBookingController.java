package com.upgrad.bookingService.controller;


import com.upgrad.bookingService.dto.BookingDTO;
import com.upgrad.bookingService.dto.PaymentDTO;
import com.upgrad.bookingService.entity.BookingInfoEntity;
import com.upgrad.bookingService.services.BookingService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/hotel")
public class RoomBookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Booking a room
     *
     * @param bookingDTO
     * @return
     */

    @PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity bookRoom(@RequestBody BookingDTO bookingDTO) {

        BookingInfoEntity savedBooking = bookingService.acceptRoomBooking(bookingDTO);

        return new ResponseEntity(savedBooking, HttpStatus.CREATED);

    }

    /**
     * Making payment
     */

    @PostMapping(value = "/booking/{bookingId}/transaction")
    public ResponseEntity makePayment(@PathVariable(name = "bookingId") int bookingId,
                                      @RequestBody PaymentDTO paymentDTO) {

            BookingInfoEntity updatedBooking = bookingService.makePayment(bookingId, paymentDTO);

            return new ResponseEntity(updatedBooking, HttpStatus.CREATED);

    }
}

