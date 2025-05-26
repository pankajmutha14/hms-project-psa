package com.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingservice.entity.BookingDate;

public interface BookingDateRepository extends JpaRepository<BookingDate, Long> {

}
