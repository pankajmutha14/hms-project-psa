package com.propertyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.propertyservice.entity.State;

public interface StateRepository extends JpaRepository<State, Long>  {
	State findByName(String name);
}
