package com.example.project.response.model;

public class Weather {
    //pojo for sending json
    private String cityName;
    private String regionName;

    public Weather(){

    }
    public Weather( String cityName,String regionName){
        this.cityName=cityName;
        this.regionName=regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
