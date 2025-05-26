package com.propertyservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.propertyservice.dto.APIResponse;
import com.propertyservice.dto.PropertyDto;
import com.propertyservice.entity.RoomAvailability;
import com.propertyservice.entity.Rooms;
import com.propertyservice.repository.RoomAvailabilityRepository;
import com.propertyservice.service.PropertyService;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
	
	@Autowired
	private PropertyService propertyService;
	
	
	
	
	
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PropertyController.class);
	
	@PostMapping(
		    value = "/add-property",
		    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,  // Ensures the endpoint accepts multipart/form-data
		    produces = MediaType.APPLICATION_JSON_VALUE
		)
		public ResponseEntity<APIResponse> addProperty(
		        @RequestParam("property") String propertyJson,  // Use RequestParam to get the property as a raw JSON string
		        @RequestParam("files") MultipartFile[] files) {  // Use RequestParam to handle files

		    // Log the multipart parts
		    logger.info("Property JSON: " + propertyJson);
		    logger.info("Number of files uploaded: " + (files != null ? files.length : 0));

		    // Parse the property JSON into PropertyDto
		    ObjectMapper objectMapper = new ObjectMapper();
		    PropertyDto dto = null;
		    try {
		        dto = objectMapper.readValue(propertyJson, PropertyDto.class);  // Convert JSON string to PropertyDto
		    } catch (JsonProcessingException e) {
		        logger.error("Error parsing property JSON", e);
		        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Handle bad JSON
		    }

		    // Process the property and files
		    PropertyDto property = propertyService.addProperty(dto, files);

		    // Create response object
		    APIResponse<PropertyDto> response = new APIResponse<>();
		    response.setMessage("Property added");
		    response.setStatus(201);
		    response.setData(property);

		    return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
	
	
		@GetMapping("/search-property")
		public APIResponse searchProperty(
		        @RequestParam String name,
		        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
		) {
		    APIResponse response = propertyService.searchProperty(name, date);
		    return response;
		}
		
		@GetMapping("/property-id")
		public APIResponse<PropertyDto> getPropertyById(@RequestParam long id){
			APIResponse<PropertyDto> response = propertyService.findPropertyById(id);
			return response;
		}
		
		@GetMapping("/room-available-room-id")
		public APIResponse<List<RoomAvailability>> getTotalRoomsAvailable(@RequestParam long id){
			List<RoomAvailability> totalRooms = propertyService.getTotalRoomsAvailable(id);
			
			APIResponse<List<RoomAvailability>> response = new APIResponse<>();
		    response.setMessage("Total rooms");
		    response.setStatus(200);
		    response.setData(totalRooms);
		    return response;
		}
		
		@GetMapping("/room-id")
		public APIResponse<Rooms> getRoomType(@RequestParam long id){
			Rooms room = propertyService.getRoomById(id);
			
			APIResponse<Rooms> response = new APIResponse<>();
		    response.setMessage("Total rooms");
		    response.setStatus(200);
		    response.setData(room);
		    return response;
		}
}
