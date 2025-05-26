package com.propertyservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.propertyservice.controller.PropertyController;
import com.propertyservice.dto.APIResponse;
import com.propertyservice.dto.EmailRequest;
import com.propertyservice.dto.PropertyDto;
import com.propertyservice.dto.RoomsDto;
import com.propertyservice.entity.Area;
import com.propertyservice.entity.City;
import com.propertyservice.entity.Property;
import com.propertyservice.entity.RoomAvailability;
import com.propertyservice.entity.Rooms;
import com.propertyservice.entity.State;
import com.propertyservice.repository.AreaRepository;
import com.propertyservice.repository.CityRepository;
import com.propertyservice.repository.PropertyRepository;
import com.propertyservice.repository.RoomAvailabilityRepository;
import com.propertyservice.repository.RoomRepository;
import com.propertyservice.repository.StateRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;


@Service
public class PropertyService {

    private final PropertyController propertyController;
	@Autowired
	private PropertyRepository propertyRepository;
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private EmailProducer emailProducer;
	@Autowired
	private RoomAvailabilityRepository availabilityRepository;
	
	
	@Autowired
	private S3Service s3Service;

    PropertyService(PropertyController propertyController) {
        this.propertyController = propertyController;
    }

	public PropertyDto addProperty(PropertyDto dto, MultipartFile[] files) {
	    Area area = areaRepository.findByName(dto.getArea());
	    City city = cityRepository.findByName(dto.getCity());
	    State state = stateRepository.findByName(dto.getState());

	    Property property = new Property();
	    property.setName(dto.getName());
	    property.setNumberOfBathrooms(dto.getNumberOfBathrooms());
	    property.setNumberOfBeds(dto.getNumberOfBeds());
	    property.setNumberOfRooms(dto.getNumberOfRooms());
	    property.setNumberOfGuestAllowed(dto.getNumberOfGuestAllowed());
	    property.setArea(area);
	    property.setCity(city);
	    property.setState(state);

	    Property savedProperty = propertyRepository.save(property);

	    // Save rooms
	    for (RoomsDto roomsDto : dto.getRooms()) {
	        Rooms rooms = new Rooms();
	        rooms.setProperty(savedProperty);
	        rooms.setRoomType(roomsDto.getRoomType());
	        rooms.setBasePrice(roomsDto.getBasePrice());
	        roomRepository.save(rooms);
	    }

	    // Upload files to S3
	    List<String> fileUrls = s3Service.uploadFiles(files);

	    // Optionally store file URLs in database or DTO
	    dto.setImageUrls(fileUrls); // Ensure PropertyDto has `List<String> imageUrls;`
	    
	    emailProducer.sendEmail(new EmailRequest(
	    	    "pankaj.p.mutha14@gmail.com",
	    	    "Property added!",
	    	    "Your property has been successfully added."
	    	));

	    return dto;
	}

	public APIResponse searchProperty(String city, LocalDate date) {
		List<Property> properties = propertyRepository.searchProperty(city,date);
		APIResponse<List<Property>> response = new APIResponse<>();
		
		response.setMessage("Search result");
		response.setStatus(200);
		response.setData(properties);
		
		return response;
	}
	
	public APIResponse<PropertyDto> findPropertyById(long id){
		APIResponse<PropertyDto> response = new APIResponse<>();
		PropertyDto dto  = new PropertyDto();
		Optional<Property> opProp = propertyRepository.findById(id);
		if(opProp.isPresent()) {
			Property property = opProp.get();
			dto.setArea(property.getArea().getName());
			dto.setCity(property.getCity().getName());
			dto.setState(property.getState().getName());
			List<Rooms> rooms = property.getRooms();
			List<RoomsDto> roomsDto = new ArrayList<>();
			for(Rooms room:rooms) {
				RoomsDto roomDto = new RoomsDto();
				BeanUtils.copyProperties(room, roomDto);
				roomsDto.add(roomDto);
			}
			dto.setRooms(roomsDto);
			BeanUtils.copyProperties(property, dto);
			response.setMessage("Matching Record");
			response.setStatus(200);
			response.setData(dto);
			return response;
		}
		
		return null;
	}

	public List<RoomAvailability> getTotalRoomsAvailable(long id) {
		return availabilityRepository.findByRoomId(id);
		
	}
	
	public Rooms getRoomById(long id) {
		return roomRepository.findById(id).get();
	}

}
