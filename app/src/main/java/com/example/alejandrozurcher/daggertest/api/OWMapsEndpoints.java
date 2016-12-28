package com.example.alejandrozurcher.daggertest.api;

import az.openweatherapi.model.gson.current_day.CurrentWeather;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by alejandro.zurcher on 12/28/2016.
 */

public interface OWMapsEndpoints {

    @GET("weather?")
    Call<CurrentWeather> getCurrentWeather(@Query("lat") double lat,
                                           @Query("lon") double lon,
                                           @Query("appid") String appId,
                                           @Query("units") String units,
                                           @Query("lang") String lang);

}
