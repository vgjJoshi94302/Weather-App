package com.example.viraljoshi.myapplication.services;

import java.util.List;

import models.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherServices {
    @GET("{lng},{lat}")
    Call<Weather> getWeather(@Path("lng") double lng,@Path("lat") double lat);
}
