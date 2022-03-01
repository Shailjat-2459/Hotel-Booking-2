package com.practice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HotelBooking2Application {

	public static void main(String[] args) {
		SpringApplication.run(HotelBooking2Application.class, args);
		System.out.println("Started...");
	}
}
