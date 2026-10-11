package com.cache.demo.service;

import com.cache.demo.entity.Weather;
import com.cache.demo.entity.WeatherRequest;

import java.util.Map;

public interface WeatherService {

    Weather saveReport(WeatherRequest weatherRequest);

    Weather getReportByState(String state);

    Weather getReportByStatesCity(String state, String city);

    Weather patchWeatherForStatesCity(String state, String city, Map<String, Long> weatherMap);

    Weather deleteWeatherForStatesCity(String state, String city);
}
