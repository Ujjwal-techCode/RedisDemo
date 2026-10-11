package com.cache.demo.controller;

import com.cache.demo.entity.Weather;
import com.cache.demo.entity.WeatherRequest;
import com.cache.demo.service.WeatherService;
import com.cache.demo.service.impl.CacheInspectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    CacheInspectionService cacheInspectionService;

    @PostMapping("/saveWeather")
    public ResponseEntity<Weather> saveWeatherReport(@Valid @RequestBody WeatherRequest weatherRequest){
        Weather savedWeather = weatherService.saveReport(weatherRequest);
        return new ResponseEntity<Weather>(savedWeather, HttpStatus.CREATED);
    }

    @GetMapping("/weatherByState/{state}")
    public ResponseEntity<Weather> getWeatherByState(@PathVariable String state){
        Weather retrievedReport = weatherService.getReportByState(state);
        return new ResponseEntity<>(retrievedReport, HttpStatus.OK);
    }

    @GetMapping("/weatherByCity/{state}/{city}")
    public ResponseEntity<Weather> getWeatherByCity(@PathVariable("state") String state, @PathVariable("city") String city){
        Weather retrievedReport = weatherService.getReportByStatesCity(state, city);
        return new ResponseEntity<>(retrievedReport, HttpStatus.OK);
    }

    @PatchMapping("/weatherByState/{state}/{city}")
    public ResponseEntity<Weather> patchWeatherByStatesCity(@PathVariable String state, @PathVariable String city, @RequestBody Map<String, Long> weatherMap) {
        Weather patchedWeather = weatherService.patchWeatherForStatesCity(state, city, weatherMap);
        return new ResponseEntity<>(patchedWeather,HttpStatus.CREATED);
    }

    @DeleteMapping("/weatherByState/{state}/{city}")
    public ResponseEntity<Weather> deleteWeatherByStatesCity(@PathVariable String state, @PathVariable String city) {
        Weather patchedWeather = weatherService.deleteWeatherForStatesCity(state, city);
        return new ResponseEntity<>(patchedWeather,HttpStatus.CREATED);
    }

    @GetMapping("/cacheData/{cacheName}")
    public void getCachedData(@PathVariable String cacheName) {
        cacheInspectionService.printCacheContents(cacheName);
    }
}
