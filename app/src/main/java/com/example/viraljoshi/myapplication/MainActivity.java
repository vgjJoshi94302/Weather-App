package com.example.viraljoshi.myapplication;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viraljoshi.myapplication.events.ErrorEvent;
import com.example.viraljoshi.myapplication.events.WeatherEvent;
import com.example.viraljoshi.myapplication.services.WeatherServiceProvider;
import com.example.viraljoshi.myapplication.services.WeatherServices;
import com.example.viraljoshi.myapplication.util.WeatherIconUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Currently;
import models.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.iconImageView)
    ImageView iconImageView;

    @BindView(R.id.summaryTextView)
    TextView summaryTextView;

    @BindView(R.id.tmptextView)
    TextView tmptextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 requesCurrentWeather(40.8267,-122.4233);
        ButterKnife.bind(this);

    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)

    public void onWeatherEvent(WeatherEvent weatherEvent)
    {
        Currently currently= weatherEvent.getWeather().getCurrently();
        tmptextView.setText(String.valueOf(Math.round(currently.getTemperature())));
        summaryTextView.setText(currently.getSummary());

        iconImageView.setImageResource(WeatherIconUtil.ICONS.get(currently.getIcon()));

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent)
    {
        Toast.makeText(this,errorEvent.getErrormessage(),Toast.LENGTH_LONG).show();
    }
    private void requesCurrentWeather(double lat, double lng) {

        WeatherServiceProvider weatherServiceProvider = new WeatherServiceProvider();
        weatherServiceProvider.getWeather(lat, lng);
    }
}
