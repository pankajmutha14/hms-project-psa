package com.bookingservice.dto;


public class PropertyDto {
	private long id;
	private String name;
	private int numberOfBeds;
	private int numberOfRooms;
	private int numberOfBathrooms;
	private int numberOfGuestAllowed;
	private String city;
	private String area;
	private String state;
	

	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getNumberOfBeds() {
		return numberOfBeds;
	}
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	public int getNumberOfBathrooms() {
		return numberOfBathrooms;
	}
	public int getNumberOfGuestAllowed() {
		return numberOfGuestAllowed;
	}
	public String getCity() {
		return city;
	}
	public String getArea() {
		return area;
	}
	public String getState() {
		return state;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public void setNumberOfBathrooms(int numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}
	public void setNumberOfGuestAllowed(int numberOfGuestAllowed) {
		this.numberOfGuestAllowed = numberOfGuestAllowed;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setState(String state) {
		this.state = state;
	}
	

}
