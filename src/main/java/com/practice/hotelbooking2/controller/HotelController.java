package com.practice.hotelbooking2.controller;

import java.util.ArrayList;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.hotelbooking2.entity.Hotel;
import com.practice.hotelbooking2.service.HotelService;

@RestController
public class HotelController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelController.class);
	
	@Autowired
	private HotelService hotelService;
	
	@RequestMapping("/city/{cityId}/hotel")
	public List<Hotel> getAllHotels(@PathVariable String cityId){
		LOGGER.debug(" HotelController : /city/{cityId}/hotel : ");
		return hotelService.getAllHotels(cityId);
	}
	
	@RequestMapping("/city/{cityId}/hotel/{hotelId}")
	public Hotel getHotel(@PathVariable Long hotelId) {
		LOGGER.debug(" >> HotelController : /city/{cityId}/hotel/{} call : ",hotelId);
		return hotelService.getHotel(hotelId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/city/{cityId}/hotel")
	public void addHotel(@Valid @RequestBody Hotel hotel, @PathVariable String cityId) {
		LOGGER.debug(" >> HotelController : /department/{departmentId}/employee : ",hotel.toString());
		hotel.setCity(new com.practice.hotelbooking2.entity.City(cityId,null));
		hotelService.addHotel(hotel);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/city/{cityId}/hotel/{hotelId}")
	public void updateHotel(@Valid @RequestBody Hotel hotel, @PathVariable String cityId, @PathVariable Long hotelId) {
		LOGGER.debug(" >> HotelController : /department/{departmentId}/employee/{id} : ",hotel.toString());
		hotel.setCity(new com.practice.hotelbooking2.entity.City(cityId,null));
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/city/{cityId}/hotel/{hotelId}")
	public void deleteHotel(@PathVariable Long hotelId) {
		LOGGER.debug(" >> HotelController : /department/{departmentId}/employee/{id} : ",hotelId);
		hotelService.deleteHotel(hotelId);
	}
	
	@GetMapping("/hotels")
	public List<Object> Employee(@RequestParam(name= "check_in",required=true,defaultValue="0000-00-00") String check_in,
			@RequestParam(name= "check_out",required=true, defaultValue="0000-00-00") String check_out,
			@RequestParam(name= "city",required=true, defaultValue="Noida") String city
			)
	{
		List<Hotel> temp1=hotelService.getAllHotels(city);
		List<Object> booking= new ArrayList<>();
		booking.add("City" +city);
		booking.add("Checkout Date" + check_out);
		booking.add("Checkin Date" + check_in);
		for(Hotel n: temp1) {
			List<Object> temp = new ArrayList<Object>();
			temp.add("Hotel Id: " +n.getHotelId());
			temp.add("Total Rooms: " +n.getTotalRooms());
			temp.add("Available Rooms: " +n.getAvailableRooms());
			booking.add(temp);
		}
		return booking;
			
		}
		
		
	}


