package com.cache.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherRequest {

    private String id;
    private String stateName;
    private String cityName;
    private long temperature;
    private long AQI;

    public WeatherRequest(String stateName, String cityName, long temperature, long AQI){
        this.stateName = stateName;
        this.cityName = cityName;
        this.temperature = temperature;
        this.AQI = AQI;
    }
}
