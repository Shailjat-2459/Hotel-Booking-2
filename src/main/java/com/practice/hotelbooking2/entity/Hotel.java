package com.practice.hotelbooking2.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Hotel implements Serializable{
	
	//Creating Table Hotel
	private static final long serialVersionUID = 1L;
	@Id
	private Long hotelId;
	
	//totalRooms should not be null
	@Min(1)
	@Max(1000)
	private Long totalRooms;
	private Long availableRooms;
	
	@ManyToOne
	private City city;

	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hotel(Long hotelId, Long totalRooms, Long availableRooms, City city) {
		super();
		this.hotelId = hotelId;
		this.totalRooms = totalRooms;
		this.availableRooms = availableRooms;
		this.city = city;
	}

	public Hotel(Long hotelId2) {
		// TODO Auto-generated constructor stub
	}

	public Hotel(Long hotelId2, Long totalRooms2, Long room) {
		// TODO Auto-generated constructor stub
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(Long totalRooms) {
		this.totalRooms = totalRooms;
	}

	public Long getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(Long availableRooms) {
		this.availableRooms = availableRooms;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	

}
