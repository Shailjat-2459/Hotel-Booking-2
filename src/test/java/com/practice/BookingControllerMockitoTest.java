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

import com.practice.hotelbooking2.controller.BookingController;
import com.practice.hotelbooking2.entity.Booking;
import com.practice.hotelbooking2.entity.City;
import com.practice.hotelbooking2.entity.Hotel;
import com.practice.hotelbooking2.service.BookingService;
import com.practice.hotelbooking2.service.HotelService;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes= {BookingControllerMockitoTest.class})
public class BookingControllerMockitoTest {
	
	@Mock
	BookingService bookingService;
	
	@Mock
	HotelService hotelService;
	
	@InjectMocks
	BookingController bookingController;
	
	List<Booking> myBookings;
	
	@Mock
	Booking booking;
	
	//@Mock
	//Hotel hotel;
	
	@Test
	@Order(1)
	public void test_getAllBookings() {
		
		myBookings=new ArrayList<Booking>();
		myBookings.add(booking);
		
		Long hotelId=(long) 200;
		
		when(bookingService.getAllBookings(hotelId)).thenReturn(myBookings);
		List<Booking> res=bookingController.getAllBookings(hotelId);
		
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
	
	/*@Test
	@Order(3)
	public void test_addBooking() {
		
		//city=new City("Bhopal",(long) 30);
		Long hotelId=(long) 200;
		String cityId="Bhopal";
		City city=new City();
		Hotel hotel=new Hotel((long) 200,(long) 30,(long) 4,city);
		booking=new Booking((long)200, null, null, hotel);
		bookingController.addBooking(booking,hotelId,cityId);
		
		verify(bookingService,times(1)).addBooking(booking);
	}*/
	
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
	
	/*@Test
	@Order(5)
	public void test_deleteBooking() {
		
		Long bookingId=(long) 300;
		Long hotelId=(long) 200;
		bookingController.deleteBooking(hotelId,bookingId);
		
		verify(bookingService,times(1)).deleteBooking(bookingId);
	}*/
}

