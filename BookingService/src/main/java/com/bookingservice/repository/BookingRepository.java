package com.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingservice.entity.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long> {

}
