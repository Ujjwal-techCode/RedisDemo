package com.cache.demo.repo;

import com.cache.demo.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, UUID> {

    Weather findByStateName(String state);

    Weather findByStateNameAndCityName(String state, String city);
}
