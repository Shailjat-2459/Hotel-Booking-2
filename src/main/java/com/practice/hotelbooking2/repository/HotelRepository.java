package com.practice.hotelbooking2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.practice.hotelbooking2.entity.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Long>{
	
	public List<Hotel> findByCity_CityId(String cityId);

}
