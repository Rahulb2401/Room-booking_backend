package com.upgrad.bookingService.services;

import com.upgrad.bookingService.dto.BookingDTO;
import com.upgrad.bookingService.dto.PaymentDTO;
import com.upgrad.bookingService.entity.BookingInfoEntity;

public interface BookingService {

    public BookingInfoEntity acceptRoomBooking(BookingDTO bookingDTO);

    public BookingInfoEntity makePayment(int bookingId, PaymentDTO paymentDTO);

}
