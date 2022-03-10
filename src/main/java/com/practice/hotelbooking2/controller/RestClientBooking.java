package com.practice.hotelbooking2.controller;

import com.practice.hotelbooking2.entity.Booking;
import com.practice.hotelbooking2.entity.City;
import com.practice.hotelbooking2.entity.Hotel;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestClientBooking {

    static RestTemplate restTemplate=new RestTemplate();

    public static void main(String args[]){
         //getAllBookings();
         //getBookingById();
         addBooking();
        // updateBooking();
       // deleteBooking();
    }

    public static void getAllBookings(){
        String cityId="Noida";
        Long hotelId=200L;
        String GET_ALL_BOOKINGS="http://localhost:8080/city/"+cityId+"/hotel/"+hotelId+"/booking";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> res = restTemplate.exchange(GET_ALL_BOOKINGS, HttpMethod.GET, entity, String.class);
        if (res.getStatusCode() == HttpStatus.OK) {
            System.out.println(res);
        } else {
            System.out.println("Internal server error");
        }
    }

    private static void getBookingById(){
        String cityId="Noida";
        Long hotelId=200L;
        Long bookingId=130L;
        String GET_BOOKING_BY_ID="http://localhost:8080/city/"+cityId+"/hotel/"+hotelId+"/booking/"+bookingId;

        Map<String, Long> param=new HashMap<>();
        param.put("bookingId",130L);
        Booking booking=restTemplate.getForObject(GET_BOOKING_BY_ID, Booking.class,param);
        System.out.println(booking.getCheckIn());
        System.out.println(booking.getCheckOut());

    }

    private static void addBooking(){
        String cityId="Noida";
        Long hotelId=200L;
        String ADD_BOOKING="http://localhost:8080/city/"+cityId+"/hotel/"+hotelId+"/booking";
        Date date1=new Date(2022-02-04);
        Date date2=new Date(2022-02-07);
        Booking booking= new Booking(131L, date1,date2,new Hotel(201L,50L,5L,new City("Noida",30L)));
        ResponseEntity<Booking> booking2=restTemplate.postForEntity(ADD_BOOKING,booking,Booking.class);
        if(booking2.getStatusCode()==HttpStatus.OK)
            System.out.println(booking2.getBody());
        else
            System.out.println("ERROR");
    }

    private static void updateBooking(){
        String cityId="Noida";
        Long hotelId=200L;
        Long bookingId=130L;
        String UPDATE_BOOKING="http://localhost:8080/city/"+cityId+"/hotel/"+hotelId+"/booking/"+bookingId;
        Map<String, Long> param=new HashMap<>();
        param.put("bookingId",130L);
        Date date1=new Date(2022-02-24);
        Date date2=new Date(2022-02-27);
        Booking updateBooking= new Booking(130L, date1,date2,new Hotel(200L,50L,5L,new City("Noida",30L)));
        restTemplate.put(UPDATE_BOOKING,updateBooking,param);
    }

    private static void deleteBooking(){
        String cityId="Noida";
        Long hotelId=200L;
        Long bookingId=130L;
        String DELETE_BOOKING="http://localhost:8080/city/"+cityId+"/hotel/"+hotelId+"/booking/"+bookingId;
        Map<String, Long> param=new HashMap<>();
        param.put("bookingId",130L);
        restTemplate.delete(DELETE_BOOKING, param);
    }
}
