package com.propertyservice.dto;



public class RoomsDto {
	
	private long id;
	
	private String roomType;
	

    private double basePrice;

	public long getId() {
		return id;
	}

	public String getRoomType() {
		return roomType;
	}

	

	public void setId(long id) {
		this.id = id;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	
    
	
}
