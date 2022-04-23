package com.example.project;


import com.example.project.exceptions.InvalidCityException;
import com.example.project.response.model.Weather;
import com.example.project.service.WeatherService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<Weather> index(@PathVariable("city") String city, @PathVariable("noOfDays") Integer days){
        //String result= null;
        try {
            JSONParser jsonParser=new JSONParser(); //coming from jsn simple library //json parser returns an object
           HttpResponse<String> unirestResponse = weatherService.getWeather(city,days);
           String result= unirestResponse.getBody(); //result is already json

            Object obj=jsonParser.parse(result); //json parser returns object
            JSONObject jsonObject= (JSONObject) obj;// converting to JSON object

            JSONObject locationObj=(JSONObject) jsonObject.get("location");

            String name=(String)locationObj.get("name");
            String region=(String)locationObj.get("region");

            System.out.println("name= "+name+" region= "+region);

            Weather weather=new Weather(city,name); //pojo for sending json

            HttpHeaders headers=new HttpHeaders();   // for adding headers
            headers.add("Name","Nidhi"); //headers can be taken as key value pairs
            return new ResponseEntity<>(weather,headers,unirestResponse.getStatus()); //here instead of result we are returning weather

           // return ResponseEntity.status(unirestResponse.getStatus()).body(result);
        } catch (InvalidCityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch(UnirestException|ParseException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }
}
