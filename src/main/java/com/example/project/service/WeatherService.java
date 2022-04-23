package com.example.project.service;
import com.example.project.accessor.WeatherAccessor;
import com.example.project.exceptions.InvalidCityException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherService {

    @Autowired
    private WeatherAccessor weatherAccessor;

    public HttpResponse<String> getWeather(String city, Integer days) throws InvalidCityException, UnirestException {
        if (city.length()<2){
            throw new InvalidCityException(city);
        }

            HttpResponse<String> response = Unirest.get("https://weatherapi-com.p.rapidapi.com/forecast.json?q="+city+"&days="+days)
                    .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "06d9388900msha47ce25c3581eedp112b38jsn8a889b0c3280")
                    .asString();
            return response;


    }
}
