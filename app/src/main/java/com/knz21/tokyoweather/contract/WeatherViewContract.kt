package com.knz21.tokyoweather.contract

import com.knz21.tokyoweather.model.Weather

interface WeatherViewContract {
    fun setWeather(weather: Weather)
}
