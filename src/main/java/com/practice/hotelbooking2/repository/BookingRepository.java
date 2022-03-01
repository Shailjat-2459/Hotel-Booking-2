package com.practice.hotelbooking2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.practice.hotelbooking2.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> {
	public List<Booking> findByHotel_HotelId(Long hotelId);

}
