package com.bookingservice.dto;

public class Rooms {
	

    private long id;

    private String roomType;
    private double basePrice;


	public long getId() {
		return id;
	}

	public String getRoomType() {
		return roomType;
	}

	public double getBasePrice() {
		return basePrice;
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


    

}
