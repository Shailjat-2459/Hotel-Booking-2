package com.practice.hotelbooking2.controller;

import com.practice.hotelbooking2.entity.City;
import com.practice.hotelbooking2.entity.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.net.http.HttpRequest.newBuilder;

public class RestClientHotel {


    static RestTemplate restTemplate=new RestTemplate();

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientCity.class);

    public static void main(String args[]) throws IOException, InterruptedException {
        //getAllHotels();
        //getHotelById();
        //addHotel();
        //updateHotel();
        //deleteHotel();
         httpGetAllHotels();
       // httpGetHotel();
        //httpAddHotel();
    }

    public static void httpGetAllHotels() throws IOException, InterruptedException {
        String cityId="Noida";
        String uri="http://localhost:8080/city/"+cityId+"/hotel";
        java.net.http.HttpRequest httpRequest= (java.net.http.HttpRequest) newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type","application/json")
                .GET()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpClient httpClient=HttpClient.newBuilder()
                .build();
        HttpResponse<String> httpResponse=httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.statusCode());
        if(httpResponse.statusCode()== 200 && httpResponse!=null) {
            System.out.println(httpResponse.body());
        }
        else{
            LOGGER.error("An exception occurred!");
        }
    }

    public static void httpGetHotel() throws IOException, InterruptedException{
        String cityId="Noida";
        Long hotelId=201L;
        String uri="http://localhost:8080/city/"+cityId+"/hotel/"+hotelId;
        java.net.http.HttpRequest httpRequest= (java.net.http.HttpRequest) newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type","application/json")
                .GET()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpClient httpClient=HttpClient.newBuilder()
                .build();
        HttpResponse<String> httpResponse=httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.statusCode());
        if(httpResponse.statusCode()== 200 && httpResponse!=null) {
            System.out.println(httpResponse.body());
        }
        else{
            LOGGER.error("An exception occurred!");
        }

    }

    public static void httpAddHotel() throws IOException, InterruptedException{
        String cityId="Noida";
        String URL_POST = "http://localhost:8080/city/"+cityId+"/hotel";
        String FILE_JSON = "/home/shailjaupdhyay/IdeaProjects/response_Hotel.json";

        // client HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Request HTTP
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .POST(java.net.http.HttpRequest.BodyPublishers.ofFile(Path.of(FILE_JSON)))
                .timeout(Duration.ofSeconds(10))
                .header("Content-Type","application/json")
                .uri(URI.create(URL_POST))
                .build();

        HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.statusCode());
        if(httpResponse.statusCode()== 200 && httpResponse!=null) {
            System.out.println(httpResponse.body());
        }
        else{
            LOGGER.error("An exception occurred!");
        }

    }


    public static void getAllHotels(){
        String cityId="Noida";
        String GET_ALL_HOTELS="http://localhost:8080/city/"+cityId+"/hotel";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> res = restTemplate.exchange(GET_ALL_HOTELS, HttpMethod.GET, entity, String.class);
        if (res.getStatusCode() == HttpStatus.OK) {
            System.out.println(res);
        } else {
            System.out.println("Internal server error");
        }
    }

    private static void getHotelById(){
        String cityId="Noida";
        Long hotelId=201L;
         String GET_HOTEL_BY_ID="http://localhost:8080/city/"+cityId+"/hotel/"+hotelId;

        Map<String, Long> param=new HashMap<>();
        param.put("hotelId",200L);
        Hotel hotel=restTemplate.getForObject(GET_HOTEL_BY_ID, Hotel.class,param);
        System.out.println(hotel.getAvailableRooms());
        System.out.println(hotel.getTotalRooms());

    }


    private static void addHotel(){
        String cityId="Noida";
        String ADD_HOTEL="http://localhost:8080/city/"+cityId+"/hotel";
        Hotel hotel=new Hotel(201L,50L,4L,new City("Noida",20L));
        ResponseEntity<Hotel> hotel2=restTemplate.postForEntity(ADD_HOTEL,hotel,Hotel.class);
        if(hotel2.getStatusCode()==HttpStatus.OK)
            System.out.println(hotel2.getBody());
        else
            System.out.println("ERROR");
    }

    private static void updateHotel(){
        String cityId="Noida";
        Long hotelId=201L;
        String UPDATE_HOTEL="http://localhost:8080/city/"+cityId+"/hotel/"+hotelId;
        Map<String, Long> param=new HashMap<>();
        param.put("hotelId",201L);
        Hotel updateHotel=new Hotel(201L,20L,4L,new City("Bhopal",20L));
        restTemplate.put(UPDATE_HOTEL,updateHotel,param);
    }

    private static void deleteHotel(){
        String cityId="Noida";
        Long hotelId=201L;
        String DELETE_HOTEL="http://localhost:8080/city/"+cityId+"/hotel/"+hotelId;
        Map<String, Long> param=new HashMap<>();
        param.put("hotelId",200L);
        restTemplate.delete(DELETE_HOTEL, param);
    }

}
