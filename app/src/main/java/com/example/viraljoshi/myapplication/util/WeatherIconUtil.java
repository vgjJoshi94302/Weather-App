package com.example.viraljoshi.myapplication.util;

import com.example.viraljoshi.myapplication.R;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class WeatherIconUtil {
    public static final Map<String,Integer> ICONS;
    static {
        Map<String, Integer> iconMap= new HashMap<>();
        iconMap.put("clear_day", R.drawable.ic_clear_day);
        iconMap.put("clear_night",R.drawable.ic_clear_night);
        iconMap.put("rain",R.drawable.ic_rain);
        iconMap.put("snow",R.drawable.ic_snow);
        iconMap.put("sleet",R.drawable.ic_sleet);
        iconMap.put("wind",R.drawable.ic_wind);
        iconMap.put("fog",R.drawable.ic_fog);
        iconMap.put("partly_cloudy_day",R.drawable.ic_partly_cloudy_day);
        iconMap.put("partly_cloudy_night",R.drawable.ic_partly_cloudy_night);
        iconMap.put("thunderstorm",R.drawable.ic_thunderstorm);
ICONS= Collections.unmodifiableMap(iconMap);
    }
}
