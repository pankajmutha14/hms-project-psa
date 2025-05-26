package com.propertyservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.propertyservice.entity.RoomAvailability;

public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailability, Long> {

	public List<RoomAvailability> findByRoomId(long id);
}
