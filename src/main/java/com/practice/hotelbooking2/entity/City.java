package com.practice.hotelbooking2.entity;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class City {
	
	@Id
	private String cityId;
	
	//number of hotels can not be empty
	@Min(1)
	@Max(200)
	private Long hotels;
	
	public City(String cityId, Long hotels) {
		super();
		this.cityId = cityId;
		this.hotels = hotels;
	}

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Long getHotels() {
		return hotels;
	}

	public void setHotels(Long hotels) {
		this.hotels = hotels;
	}
	
	
	

}
