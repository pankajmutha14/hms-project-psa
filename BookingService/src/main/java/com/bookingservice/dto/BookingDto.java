package com.bookingservice.dto;

import java.time.LocalDate;
import java.util.List;

public class BookingDto {

	private long propertyId;
	private long roomId;
	private long roomAvailabilityId;
	private String name;
	private String email;
	private String mobile; 
	private String status;
	private double price;
	private int totalNigths;
	private double totalPrice;
	private List<LocalDate> date;
	
	
	public double getPrice() {
		return price;
	}
	public int getTotalNigths() {
		return totalNigths;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setTotalNigths(int totalNigths) {
		this.totalNigths = totalNigths;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getPropertyId() {
		return propertyId;
	}
	public long getRoomId() {
		return roomId;
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
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public long getRoomAvailabilityId() {
		return roomAvailabilityId;
	}
	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public void setRoomAvailabilityId(long roomAvailabilityId) {
		this.roomAvailabilityId = roomAvailabilityId;
	}
	public List<LocalDate> getDate() {
		return date;
	}
	public void setDate(List<LocalDate> date) {
		this.date = date;
	}
	
	
	
}
