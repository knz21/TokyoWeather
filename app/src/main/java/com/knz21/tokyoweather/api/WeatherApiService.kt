package com.knz21.tokyoweather.api

import com.knz21.tokyoweather.model.Weather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/forecast/webservice/json/v1")
    fun getWeather(@Query("city") city: Int): Single<Weather>
}
