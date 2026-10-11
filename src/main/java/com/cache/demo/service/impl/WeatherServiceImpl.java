package com.cache.demo.service.impl;

import com.cache.demo.entity.Weather;
import com.cache.demo.entity.WeatherRequest;
import com.cache.demo.repo.WeatherRepository;
import com.cache.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    CacheInspectionService cacheInspectionService;

    @Override
    public Weather saveReport(WeatherRequest weatherRequest){

        String sessionId = "100100-1100-4321";
        cacheInspectionService.setSessionVariable(sessionId,"Ujjwal", "Kumar" );
        cacheInspectionService.setSessionVariable(sessionId,"Roshan", "Shahi" );

        Weather weather = new Weather(weatherRequest.getStateName(), weatherRequest.getCityName(), weatherRequest.getTemperature(), weatherRequest.getAQI());
        return weatherRepository.save(weather);
    }

    @Cacheable(value = "weather", key = "#state")
    @Override
    public Weather getReportByState(String state) {

        String sessionId = "100100-1100-4321";
        String cacheValue1 = cacheInspectionService.getSessionVariable(sessionId,"Ujjwal");
        String cacheValue2 = cacheInspectionService.getSessionVariable(sessionId,"Roshan");
        System.out.println("cacheValue1 : "+cacheValue1);
        System.out.println("cacheValue2 : "+cacheValue2);

        System.out.println("findByState was hitted in DB");
        return weatherRepository.findByStateName(state);
    }

    @Cacheable(value = "weather", key = "#city")
    @Override
    public Weather getReportByStatesCity(String state, String city) {
        System.out.println("findByStateAndCity was hitted by DB");
        return weatherRepository.findByStateNameAndCityName(state, city);
    }

    @CachePut(value = "weather", key = "#state")
    @Override
    public Weather patchWeatherForStatesCity(String state, String city, Map<String, Long> weatherMap){
        Weather weather = weatherRepository.findByStateNameAndCityName(state,city);
        for(String s : weatherMap.keySet()){
            if(s.equals("temperature")) weather.setTemperature(weatherMap.get("temperature"));
            if(s.equals("AQI")) weather.setAQI(weatherMap.get("AQI"));
            weatherRepository.save(weather);
        }
        return weather;
    }

    @CacheEvict(value = "weather", key = "#state")
    @Override
    public Weather deleteWeatherForStatesCity(String state, String city) {
        Weather weather = weatherRepository.findByStateNameAndCityName(state,city);
        if(Objects.nonNull(weather)) {
            weatherRepository.delete(weather);
        }
        return weather;
    }
}