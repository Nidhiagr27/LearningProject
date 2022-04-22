package com.example.project;


import com.example.project.exceptions.InvalidCityException;
import com.example.project.service.WeatherService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/{city}/{noOfDays}")
    public ResponseEntity<String> index(@PathVariable("city") String city, @PathVariable("noOfDays") Integer days){
        //String result= null;
        try {
           HttpResponse<String> unirestResponse = weatherService.getWeather(city,days);
           String result= unirestResponse.getBody();
            return ResponseEntity.status(unirestResponse.getStatus()).body(result);
        } catch (InvalidCityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch(UnirestException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }


    }
}
