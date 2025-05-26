package com.propertyservice.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "room_availability")
public class RoomAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate availableDate;
    private int availableCount;
    private double price;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Rooms room;

	public long getId() {
		return id;
	}

	public LocalDate getAvailableDate() {
		return availableDate;
	}

	public int getAvailableCount() {
		return availableCount;
	}

	public double getPrice() {
		return price;
	}

	public Rooms getRoom() {
		return room;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAvailableDate(LocalDate availableDate) {
		this.availableDate = availableDate;
	}

	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRoom(Rooms room) {
		this.room = room;
	}

    // Getters and Setters...
    
    
    
}
