package com.practice;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.hotelbooking2.entity.City;
import com.practice.hotelbooking2.repository.CityRepository;
import com.practice.hotelbooking2.service.CityService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes= {ServiceMokitoTests.class})
public class ServiceMokitoTests {
	
	@Mock
	CityRepository cityRepository;
	
	@InjectMocks
	CityService cityService;
	
	public List<City> myCities;
	
	@Test
	@Order(1)
	public void test_getAllCities() {
		
		List<City> myCities=new ArrayList<City>();
		myCities.add(new City("Bangalore",(long) 40));
		myCities.add(new City("Kerala",(long) 30));
		
		when(cityRepository.findAll()).thenReturn(myCities); //Mocking
		assertEquals(2,cityService.getAllCities().size());
	}
	
	@Test
	@Order(2)
	public void test_getCity() {
		Optional<City> city= Optional.ofNullable(new City("Bangalore",6L));
		String cityId="Bangalore";
		
		when(cityRepository.findById(cityId)).thenReturn(city); //Mocking
		assertEquals(cityId,cityService.getCity(cityId).getCityId());
	}
	
	@Test
	@Order(3)
	public void test_addCity() {
		City city=new City("Hyderabad",(long) 20);
		
		when(cityRepository.save(city)).thenReturn(city);
		assertEquals(city,cityService.addCity(city));
	}
	
	@Test
	@Order(4)
	public void test_updateCity() {
		City city=new City("Hyderabad",(long) 20);
		
		when(cityRepository.save(city)).thenReturn(city);
		String cityId="Hyderabad";
		assertEquals(city,cityService.updateCity(city,cityId));
	}
	
	@Test
	@Order(5)
	public void test_deleteCity() {
		String cityId="Hyderabad";
		cityService.deleteCity(cityId);
		verify(cityRepository,times(1)).deleteById(cityId);
	}

}
