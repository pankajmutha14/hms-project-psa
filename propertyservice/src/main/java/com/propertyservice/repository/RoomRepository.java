package com.propertyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.propertyservice.entity.Rooms;

public interface RoomRepository extends JpaRepository<Rooms, Long> {
	
	

}
