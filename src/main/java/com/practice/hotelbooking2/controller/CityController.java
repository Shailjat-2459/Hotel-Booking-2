package com.practice.hotelbooking2.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.hotelbooking2.entity.City;
import com.practice.hotelbooking2.service.CityService;


@RestController
public class CityController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/city")
	public List<City> getAllCities(){
		LOGGER.debug("CityController : /city : ");
		return cityService.getAllCities();
	}
	
	@RequestMapping("/city/{cityId}")
	public City getCity(@PathVariable String cityId) {
		LOGGER.debug("CityController : /city/{} call : ",cityId);
		return cityService.getCity(cityId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/city")
	public void addCity(@Valid @RequestBody City city) {
		LOGGER.debug("CityController : /city : ",city.toString());
		cityService.addCity(city);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/city/{cityId}")
	public void updateCity(@Valid @RequestBody City city,@PathVariable String cityId) {
		LOGGER.debug("CityService : Entering updateCity");
		cityService.updateCity(city,cityId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/city/{cityId}")
	public void deleteCity(@PathVariable String cityId) {
		LOGGER.debug("CityController : /city/{cityId} : ",cityId);
		cityService.deleteCity(cityId);
	}
	
	
	

}
