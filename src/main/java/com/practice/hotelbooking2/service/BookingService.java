package com.practice.hotelbooking2.service;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.hotelbooking2.entity.Booking;
import com.practice.hotelbooking2.exception.ResourceNotFoundException;
import com.practice.hotelbooking2.repository.BookingRepository;

@Service
public class BookingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Transactional
	public List<Booking> getAllBookings(Long hotelId){
		LOGGER.debug("BookingService : Entering getAll");
		List<Booking> bookings=new ArrayList<>();
		bookingRepository.findByHotel_HotelId(hotelId).forEach(bookings :: add);
		LOGGER.debug("BookingService : Exiting getAll");
		return bookings;	
	}
	
	@Transactional
	public Booking getBooking(Long bookingId) {
		LOGGER.debug("Bookingservice : Entering getBooking");
		return bookingRepository.findById(bookingId)
				.orElseThrow(()-> new ResourceNotFoundException("Booking not found with booking id: " +bookingId));
		}
	
	@Transactional
	public void addBooking(Booking booking) {
		LOGGER.debug("BookingService : Entering addBooking");
		bookingRepository.save(booking);
	}
	
	@Transactional
   public void updateBooking(Booking booking) {
	   LOGGER.debug("BookingService : Entering updateEmployee");
		bookingRepository.save(booking);
	}
   
	@Transactional
   public void deleteBooking(Long bookingId) {
	   LOGGER.debug("BookingService : Employee deleted : Exiting deleteEmployee");
		bookingRepository.deleteById(bookingId);	
	}
	

}
