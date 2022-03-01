package com.practice.hotelbooking2.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.hotelbooking2.entity.Hotel;
import com.practice.hotelbooking2.exception.ResourceNotFoundException;
import com.practice.hotelbooking2.repository.HotelRepository;

@Service
public class HotelService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelService.class);
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Transactional
	public List<Hotel> getAllHotels(String cityId){
		LOGGER.debug(" HotelService : Entering getAll");
		List<Hotel> hotels=new ArrayList<>();
		hotelRepository.findByCity_CityId(cityId).forEach(hotels:: add);
		return hotels;
	}
	
	@Transactional
	public Hotel getHotel(Long hotelId) {
		LOGGER.debug(" HotelService : Entering getHotel");
        LOGGER.debug(" HotelService : Exiting getHotel");
		return hotelRepository.findById(hotelId)
				.orElseThrow(()-> new ResourceNotFoundException("Hotel not found with hotelid :" + hotelId));
		
	}
	
	@Transactional
	public void addHotel(Hotel hotel) {
		LOGGER.debug(" HotelService : Entering addHotel");
		hotelRepository.save(hotel);
		LOGGER.debug("HotelService : Exiting addHotel");
	}
	
	@Transactional
	public void updateHotel(Hotel hotel) {
		LOGGER.debug("HotelService : Entering updateHotel");
		hotelRepository.save(hotel);
	}
	
	@Transactional
	public void deleteHotel(Long hotelId) {
		LOGGER.debug("HotelService :Hotel deleted : Exiting deleteHotel");
		hotelRepository.deleteById(hotelId);
	}

}
