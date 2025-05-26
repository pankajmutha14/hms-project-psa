package com.bookingservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name="bookings")
public class Bookings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	private String mobile;
	private String propertyName;
	private double totalPrice; 
	private String status;
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getMobile() {
		return mobile;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
