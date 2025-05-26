package com.bookingservice.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="booking_date")
public class BookingDate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="booking_id")
	private Bookings bookings;
	

	public long getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public Bookings getBookings() {
		return bookings;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setBookings(Bookings bookings) {
		this.bookings = bookings;
	}


}
