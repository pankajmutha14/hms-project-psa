package com.propertyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.propertyservice.entity.Area;

public interface AreaRepository extends JpaRepository<Area, Long>  {
	
	Area findByName(String name);

}
