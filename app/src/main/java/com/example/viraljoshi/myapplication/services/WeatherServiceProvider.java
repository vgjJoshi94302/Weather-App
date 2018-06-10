package com.example.viraljoshi.myapplication.services;

import android.util.Log;
import android.widget.Toast;
import com.example.viraljoshi.myapplication.events.ErrorEvent;
import com.example.viraljoshi.myapplication.events.WeatherEvent;
import org.greenrobot.eventbus.EventBus;
import models.Currently;
import models.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class WeatherServiceProvider {

    private static final String BASE_URL = "https://api.darksky.net/forecast/085626ece937f4e3d72a3ba26742c6b4/" ;
    private static final String TAG = WeatherServiceProvider.class.getSimpleName();
    private Retrofit retrofit;
    Retrofit getRetrofit() {
        if (retrofit == null)
        {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
    return retrofit;
    }
    public void getWeather(double lng, double lat)
    {
        WeatherServices weatherServices = getRetrofit().create(WeatherServices.class);
        Call<Weather> weatherData = weatherServices.getWeather(lng,lat);
        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather= response.body();
                if(weather!=null)
                {
                Currently currently=weather.getCurrently();
                Log.e(TAG,"Temperature = "+ currently.getTemperature());
                EventBus.getDefault().post(new WeatherEvent(weather));
                }
                else
                {
                    Log.e(TAG,"No Response:Check secret key");
                    EventBus.getDefault().post(new ErrorEvent("No Weather data available"));
                }
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG,"OnFailure: Unable to get the Weather data");
                EventBus.getDefault().post(new ErrorEvent("Unable to connect weather server"));
            }
        });
    }
}