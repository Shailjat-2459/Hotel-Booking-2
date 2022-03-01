package com.practice;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.hotelbooking2.controller.HotelController;
import com.practice.hotelbooking2.entity.City;
import com.practice.hotelbooking2.entity.Hotel;
import com.practice.hotelbooking2.service.HotelService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes= {HotelControllerMockitoTest.class})
public class HotelControllerMockitoTest {
	
	@Mock
	HotelService hotelService;
	
	@InjectMocks
	HotelController hotelController;
	
	List<Hotel> myHotels;

	@Mock
	Hotel hotel;

	@Mock
	City city;
	
	@Test
	@Order(1)
	public void test_getAllHotels() {
		
		myHotels=new ArrayList<Hotel>();
		myHotels.add(hotel);
		
		String cityId="Bhopal";
		
		when(hotelService.getAllHotels(cityId)).thenReturn(myHotels);
		List<Hotel> res=hotelController.getAllHotels(cityId);
		
		assertEquals(1,res.size());
	}
	
	/*@Test
	@Order(2)
	public void test_getHotel() {
		
		Long hotelId=(long) 200;
		
		when(hotelService.getHotel(hotelId)).thenReturn(hotel);
		Hotel res=hotelController.getHotel(hotelId);
		
		assertEquals(hotelId,res.getHotelId());
		
	}*/
	
	@Test
	@Order(3)
	public void test_addHotel() {
		
		//city=new City("Bhopal",(long) 30);
		String cityId="Bhopal";
		hotelController.addHotel(hotel,cityId);
		
		verify(hotelService,times(1)).addHotel(hotel);
	}
	
	/*@Test
	@Order(4)
	public void test_updateHotel() {
		
		//hotel=new Hotel((long) 200,(long) 30,(long) 4,city);
		Long hotelId=(long) 200;
		String cityId="Bhopal";
		
		when(hotelService.getHotel(hotelId)).thenReturn(hotel);
		hotelController.updateHotel(hotel, cityId,hotelId);
		
		verify(hotelService,times(1)).updateHotel(hotel);
		
	}*/
	
	@Test
	@Order(5)
	public void test_deleteHotel() {
		
		Long hotelId=(long) 200;
		hotelController.deleteHotel(hotelId);;
		
		verify(hotelService,times(1)).deleteHotel(hotelId);
	}
}
