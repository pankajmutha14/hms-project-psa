package com.propertyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.propertyservice.entity.City;

public interface CityRepository extends JpaRepository<City, Long>  {
	City findByName(String name);
}
