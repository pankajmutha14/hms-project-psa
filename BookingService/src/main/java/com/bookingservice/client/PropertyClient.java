package com.bookingservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookingservice.dto.APIResponse;
import com.bookingservice.dto.PropertyDto;
import com.bookingservice.dto.RoomAvailability;

@FeignClient(name = "PROPERTYSERVICE") 
public interface PropertyClient {
	
	@GetMapping("/api/v1/property/property-id")
	public APIResponse<PropertyDto> getPropertyById(@RequestParam long id);
	
	@GetMapping("/api/v1/property/room-available-room-id")
	public APIResponse<List<RoomAvailability>> getTotalRoomsAvailable(@RequestParam long id);


	@GetMapping("/api/v1/property/room-id")
	public APIResponse<com.bookingservice.dto.Rooms> getRoomType(@RequestParam long id);

}
