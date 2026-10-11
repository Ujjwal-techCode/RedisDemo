package com.cache.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "weather")
@Data
@NoArgsConstructor
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String stateName;
    private String cityName;
    private long temperature;
    private long AQI;

    public Weather(String stateName, String cityName, long temperature, long AQI){
        this.stateName = stateName;
        this.cityName = cityName;
        this.temperature = temperature;
        this.AQI = AQI;
    }
}