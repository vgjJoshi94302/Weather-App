package com.example.viraljoshi.myapplication.events;

import models.Weather;

public class WeatherEvent {
    private final Weather weather;

    public WeatherEvent(Weather weather) {

        this.weather=weather;
    }

    public Weather getWeather() {
        return weather;
    }
}
