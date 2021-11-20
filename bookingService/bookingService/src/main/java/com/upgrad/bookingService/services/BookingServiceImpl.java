package com.upgrad.bookingService.services;

import com.upgrad.bookingService.dao.BookingDAO;
import com.upgrad.bookingService.dto.BookingDTO;
import com.upgrad.bookingService.dto.PaymentDTO;
import com.upgrad.bookingService.entity.BookingInfoEntity;
import com.upgrad.bookingService.exception.InvalidBookingId;
import com.upgrad.bookingService.exception.InvalidPaymentMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
private BookingDAO bookingDAO;

    @Autowired
    private RestTemplate restTemplate;

    final int statusCode = 400;
    final String INVALID_BOOKING_ID = "Invalid booking Id";
    final String INVALID_PAYMENT_MODE = "Invalid mode of payment";

    @Override
    public BookingInfoEntity acceptRoomBooking(BookingDTO bookingDTO) {

        BookingInfoEntity bookingInfoEntity = new BookingInfoEntity();

        bookingInfoEntity.setFromDate(bookingDTO.getFromDate());
        bookingInfoEntity.setToDate(bookingDTO.getToDate());
        bookingInfoEntity.setAadharNumber(bookingDTO.getAadharNumber());
        bookingInfoEntity.setNumOfRooms(bookingDTO.getNumOfRooms());
        bookingInfoEntity.setRoomNumbers(getRandomRoomNumbers(bookingDTO.getNumOfRooms()));


        int numOfDays = (int) ChronoUnit.DAYS.between(bookingDTO.getFromDate(), bookingDTO.getToDate());
        bookingInfoEntity.setRoomPrice(1000*bookingDTO.getNumOfRooms()*numOfDays);

        bookingInfoEntity.setBookedOn(LocalDate.now());


        return bookingDAO.save(bookingInfoEntity);
    }

    @Override
    public BookingInfoEntity makePayment(int bookingId, PaymentDTO paymentDTO) {

        String paymentUrl = "http://localhost:8083/payment/transaction";
        Integer transactionId = restTemplate.postForObject(paymentUrl, paymentDTO, Integer.class);

        Optional<BookingInfoEntity> bookingInfoEntity = bookingDAO.findById(paymentDTO.getBookingId());

//        Validation of Booking ID
        if (bookingInfoEntity.isPresent() && (paymentDTO.getPaymentMode().equalsIgnoreCase("card") ||
                paymentDTO.getPaymentMode().equalsIgnoreCase("upi"))) {


                BookingInfoEntity bookingInfo = bookingInfoEntity.get();
                bookingInfo.setTransactionId(transactionId);
                bookingDAO.save(bookingInfo);
                String message = "Booking confirmed for user with aadhaar number: "
                        + bookingInfo.getAadharNumber()
                        + "    |    "
                        + "Here are the booking details:    " + bookingInfo.toString();
                System.out.println(message);
                return bookingInfo;

        }
        else if(!bookingInfoEntity.isPresent()){
            throw new InvalidBookingId(INVALID_BOOKING_ID, statusCode);
        }

        else if(!(paymentDTO.getPaymentMode().equalsIgnoreCase("card")) ||
                !(paymentDTO.getPaymentMode().equalsIgnoreCase("upi"))){
            throw new InvalidPaymentMode(INVALID_PAYMENT_MODE, statusCode);
        }

        return null;

    }



    //    Function for random room numbers
public String getRandomRoomNumbers(int count){
    Random rand = new Random();
    int upperBound = 100;

    String roomNumbers = "";

    for (int i=0; i<count; i++){
        roomNumbers+=String.valueOf(rand.nextInt(upperBound));
        if(i<count-1)
            roomNumbers+=",";
    }

    return roomNumbers;
}
}
