package com.upgrad.bookingService.dao;

import com.upgrad.bookingService.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookingDAO extends JpaRepository<BookingInfoEntity, Integer> {

}
