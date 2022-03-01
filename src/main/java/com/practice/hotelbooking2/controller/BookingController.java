package com.practice.hotelbooking2.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.practice.hotelbooking2.entity.Booking;
import com.practice.hotelbooking2.entity.City;
import com.practice.hotelbooking2.entity.Hotel;
import com.practice.hotelbooking2.service.BookingService;
import com.practice.hotelbooking2.service.CityService;
import com.practice.hotelbooking2.service.HotelService;

@RestController
public class BookingController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping("/city/{cityId}/hotel/{hotelId}/booking")
	public List<Booking> getAllBookings(@PathVariable Long hotelId){
		LOGGER.debug("BookingController : /city/{cityId}/hotel/{hotelId}/booking: ");
		return bookingService.getAllBookings(hotelId);
	}
	
	@RequestMapping("/city/{cityId}/hotel/{hotelId}/booking/{bookingId}")
	public Booking getBooking(@PathVariable Long bookingId) {
		LOGGER.debug(" BookingController : /city/{cityId}/hotel/{hotelId}/booking/{} call : ",bookingId);
		return bookingService.getBooking(bookingId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/city/{cityId}/hotel/{hotelId}/booking")
	public void addBooking(@RequestBody Booking booking, @PathVariable Long hotelId,@PathVariable String cityId) {
		LOGGER.debug(" >> BookingController : /city/{cityId}/hotel/{hotelId}/booking : ",booking.toString());
		Hotel hotel=hotelService.getHotel(hotelId);
		if(hotel.getAvailableRooms()==0) {
			System.out.println("All rooms are occupied");
		}
		else {
			City city=cityService.getCity(cityId);
			Long room = hotel.getAvailableRooms()-1;
			Hotel temp = new Hotel(hotel.getHotelId(),hotel.getTotalRooms(),room,city);
			hotelService.updateHotel(temp);
			booking.setHotel(temp);
			bookingService.addBooking(booking);
			}
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/city/{cityId}/hotel/{hotelId}/booking/{bookingId}")
	public void updateBooking(@RequestBody Booking booking, @PathVariable Long hotelId, @PathVariable Long bookingId) {
		LOGGER.debug("BookingController : /city/{cityId}/hotel/{hotelId}/booking/{bookingId} : ",booking.toString());
		booking.setHotel(new Hotel(hotelId));
		bookingService.updateBooking(booking);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/city/{cityId}/hotel/{hotelId}/booking/{bookingId}")
	public void deleteBooking(@PathVariable Long hotelId, @PathVariable Long bookingId) {
		LOGGER.debug(" BookingController :/city/{cityId}/hotel/{hotelId}/booking/{bookingId} ",bookingId);
		bookingService.deleteBooking(bookingId);
		Hotel hotel=hotelService.getHotel(hotelId);
		Long room=hotel.getAvailableRooms()+1;
		Hotel temp=new Hotel(hotel.getHotelId(),hotel.getTotalRooms(),room);
		hotelService.updateHotel(temp);
		
	}
	

}
