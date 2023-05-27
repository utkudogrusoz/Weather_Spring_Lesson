package com.utkudogrusoz.weather.controller;

import com.utkudogrusoz.weather.controller.validation.CityNameConstraint;
import com.utkudogrusoz.weather.dto.WeatherDto;
import com.utkudogrusoz.weather.service.WeatherService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/weather")
@Validated
public class WeatherAPI {
    private final WeatherService weatherService;
    public WeatherAPI(WeatherService weatherService){
        this.weatherService=weatherService;
    }

    @GetMapping("/{city}")
    @RateLimiter(name="basic")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable("city") @CityNameConstraint @NotBlank String city){
        return ResponseEntity.ok(weatherService.getWeatherByCityName(city));
    }
}
