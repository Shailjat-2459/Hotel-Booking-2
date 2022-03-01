package com.practice;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.hotelbooking2.controller.CityController;
import com.practice.hotelbooking2.entity.City;
import com.practice.hotelbooking2.service.CityService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes= {ControllerMockitoTests.class})
public class ControllerMockitoTests {
	
	@Mock
	CityService cityService;
	
	@InjectMocks
	CityController cityController;
	
	List<City> myCities;
	City city;
	
	@Test
	@Order(1)
	public void test_getAllCities() {
		
		myCities=new ArrayList<City>();
		myCities.add(new City("Bangalore",(long) 40));
		myCities.add(new City("Kerala",(long) 30));
		
		when(cityService.getAllCities()).thenReturn(myCities); //Mocking
		List<City> res=cityController.getAllCities();
		
		assertEquals(2,res.size());
	}
	
	@Test
	@Order(2)
	public void test_getCity() {
		city=new City("Bangalore",(long) 30);
		String cityId="Bangalore";
		
		when(cityService.getCity(cityId)).thenReturn(city);
		City res=cityController.getCity(cityId);
		
		assertEquals(cityId,res.getCityId());
	}
	
	@Test
	@Order(3)
	public void test_addCity() {
		
		city=new City("Bhopal",(long) 30);
		cityController.addCity(city);
		
		verify(cityService,times(1)).addCity(city);
	}
	
	@Test
	@Order(4)
	public void test_updateCity() {
		
		city=new City("Bhopal",(long) 30);
		String cityId="Bhopal";
		
		when(cityService.getCity(cityId)).thenReturn(city);
		cityController.updateCity(city, cityId);
		
		verify(cityService,times(1)).updateCity(city, cityId);
		
	}
	
	@Test
	@Order(5)
	public void test_deleteCity() {
		
		String cityId="Bhopal";
		cityController.deleteCity(cityId);
		
		verify(cityService,times(1)).deleteCity(cityId);
	}
	
	
	
	
}
