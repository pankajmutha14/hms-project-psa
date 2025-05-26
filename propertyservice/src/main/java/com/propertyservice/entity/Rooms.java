package com.propertyservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="rooms")
public class Rooms {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roomType;

    @Column(name = "base_price")
    private double basePrice;

    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonBackReference
    private Property property;

	public long getId() {
		return id;
	}

	public String getRoomType() {
		return roomType;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public Property getProperty() {
		return property;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

    

}
