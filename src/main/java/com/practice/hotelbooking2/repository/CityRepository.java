package com.practice.hotelbooking2.repository;

import org.springframework.data.repository.CrudRepository;

import com.practice.hotelbooking2.entity.City;

public interface CityRepository extends CrudRepository<City, String> {

}
