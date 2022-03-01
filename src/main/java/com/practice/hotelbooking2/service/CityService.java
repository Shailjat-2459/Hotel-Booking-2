package com.practice.hotelbooking2.service;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.hotelbooking2.exception.ResourceNotFoundException;
import com.practice.hotelbooking2.entity.City;
import com.practice.hotelbooking2.repository.CityRepository;

@Service
public class CityService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);
	
	private static ReentrantLock lock= new ReentrantLock();
	
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional
	public List<City> getAllCities(){
		LOGGER.debug("CityService : Entering getAllCities");
		List<City> cities=new ArrayList<>();
		lock.lock();
		try {
			cityRepository.findAll().forEach(cities::add);
		}
		finally {
			lock.unlock();
		}
		LOGGER.debug("CityService : Exiting getAllCities");
		return cities;
		}
	
	@Transactional
	public City getCity(String cityId) {
		lock.lock();
		LOGGER.debug("CityService : Entering getCity");
		try {
			return cityRepository.findById(cityId)
					.orElseThrow(()-> new ResourceNotFoundException("City Not Found with CityName:" + cityId));
		}
		finally {
			lock.unlock();
		}
	}
	
	@Transactional
	public City addCity(City city) {
		lock.lock();
		LOGGER.debug("CityService : Entering addCity");
		try {
			cityRepository.save(city);
		}
		finally {
			lock.unlock();
		}
		LOGGER.info("Adding to the City...");
  	    LOGGER.debug("CityService : Exiting addCity");
  	    
		return city;
	}
	
	@Transactional
	public City updateCity(City city, String cityId) {
		lock.lock();
		LOGGER.debug("CityService : Entering updateCity");
		try {
			LOGGER.debug("CityService : City updated : Exiting updateCity");
			cityRepository.save(city);
        }
		finally {
			lock.unlock();
		}
		return city;
	}
	
	@Transactional
	public void deleteCity(String cityId) {
		lock.lock();
		LOGGER.debug("CityService :City deleted : Entering deleteCity");
		try {
			cityRepository.deleteById(cityId);
		}
		finally {
			lock.unlock();
		}
		LOGGER.debug("CityService :City deleted : Exiting deleteCity");
	}

}
