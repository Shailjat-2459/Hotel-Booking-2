package com.practice.hotelbooking2.controller;

import com.practice.hotelbooking2.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static java.net.http.HttpRequest.newBuilder;

//import org.apache.http.client.methods.HttpPost;


public class RestClientCity {

    private static final String GET_ALL_CITIES="http://localhost:8080/city";
    private static final String GET_CITY_BY_ID="http://localhost:8080/city/{cityId}";
    private static final String ADD_CITY="http://localhost:8080/city";
    private static final String UPDATE_CITY="http://localhost:8080/city/{cityId}";
    private static final String DELETE_CITY="http://localhost:8080/city/{cityId}";
    private static final String uri="http://localhost:8080/city";


    static RestTemplate restTemplate=new RestTemplate();

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientCity.class);
    public static void main(String args[]) throws IOException, InterruptedException {
        //getAllCities();
        //getCityById();
        //addCity();
        //updateCity();
        //deleteCity();
         httpGetAllCities();
       // httpGetCity();
        // httpAddCity();
       // httpGetAllCitiesAsync(uri);
         // httpUpdateCity();
        //httpDeleteCity();
    }

    public static void httpGetAllCities() throws IOException, InterruptedException {
        String uri="http://localhost:8080/city";
        HttpRequest httpRequest= (HttpRequest) newBuilder()
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
            LOGGER.info(httpResponse.body());
        }
        else{
            LOGGER.error("An exception occurred!");
        }
    }

    //Asynchronous Get()
    public static CompletableFuture<String> httpGetAllCitiesAsync(String uri){
        HttpClient httpClient=HttpClient.newHttpClient();
        HttpRequest httpRequest=(HttpRequest) newBuilder()
                .uri(URI.create(uri))
                .build();
        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);

    }

    public static void httpGetCity() throws IOException, InterruptedException{
        String uri="http://localhost:8080/city/Mumbai";
        HttpRequest httpRequest= (HttpRequest) newBuilder()
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
            LOGGER.info(httpResponse.body());
        }
        else{
            LOGGER.error("An exception occurred!");
        }

    }

     public static void httpAddCity() throws IOException, InterruptedException{
         String URL_POST = "http://localhost:8080/city";
         String FILE_JSON = "/home/shailjaupdhyay/IdeaProjects/response.json";

             // client HTTP
             HttpClient client = HttpClient.newHttpClient();

             // Request HTTP
             HttpRequest request = HttpRequest.newBuilder()
                     .POST(HttpRequest.BodyPublishers.ofFile(Path.of(FILE_JSON)))
                     .timeout(Duration.ofSeconds(10))
                     .header("Content-Type","application/json")
                     .uri(URI.create(URL_POST))
                     .build();

         HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
         System.out.println(httpResponse.statusCode());
         if(httpResponse.statusCode()== 200 && httpResponse!=null) {
             LOGGER.info(httpResponse.body());
         }
         else{
             LOGGER.error("An exception occurred!");
         }


            /* client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                     .thenApply(HttpResponse::body)
                     .thenAccept(System.out::println)
                     .join();*/
    }

    public static void httpUpdateCity() throws IOException, InterruptedException {
        String URL_PUT = "http://localhost:8080/city/Mumbai";
        String FILE_JSON = "/home/shailjaupdhyay/IdeaProjects/response.json";

        // client HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Request HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofFile(Path.of(FILE_JSON)))
                .timeout(Duration.ofSeconds(10))
                .header("Content-Type","application/json")
                .uri(URI.create(URL_PUT))
                .build();

        HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.statusCode());
        if(httpResponse.statusCode()== 200 && httpResponse!=null) {
            LOGGER.info(httpResponse.body());
        }
        else{
            LOGGER.error("An exception occurred!");
        }
    }

    public static void httpDeleteCity() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/city/Mumbai";
        HttpRequest httpRequest = (HttpRequest) newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .DELETE()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.statusCode());
        if (httpResponse.statusCode() == 200 && httpResponse != null) {
            LOGGER.info(httpResponse.body());
        } else {
            LOGGER.error("An exception occurred!");
        }
    }

    public static void getAllCities(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> res = restTemplate.exchange(GET_ALL_CITIES, HttpMethod.GET, entity, String.class);
        if (res.getStatusCode() == HttpStatus.OK) {
            System.out.println(res);
        } else {
            System.out.println("Internal server error");
        }

    }

    private static void getCityById(){
        Map<String, String> param=new HashMap<>();
        param.put("cityId","Noida");

        City city=restTemplate.getForObject(GET_CITY_BY_ID, City.class,param);

        System.out.println(city.getHotels());
    }

    private static void addCity(){
        City city=new City("Hyderabad",20L);

        ResponseEntity<City> city2=restTemplate.postForEntity(ADD_CITY,city,City.class);
        if(city2.getStatusCode()==HttpStatus.OK)
            System.out.println(city2.getBody());
        else
            System.out.println("ERROR");
    }

    private static void updateCity(){
        Map<String, String> param=new HashMap<>();
        param.put("cityId","Bhopal");

        City updateCity=new City("Bhopal",20L);
       restTemplate.put(UPDATE_CITY,updateCity,param);

    }

    private static void deleteCity(){
        Map<String, String> param=new HashMap<>();
        param.put("cityId","Bhopal");

        restTemplate.delete(DELETE_CITY, param);
    }

}
