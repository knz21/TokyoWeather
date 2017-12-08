package com.knz21.tokyoweather.viewmodel

import com.knz21.tokyoweather.api.WeatherApiService
import com.knz21.tokyoweather.contract.WeatherViewContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val CITY_CODE_TOKYO = 130010

class WeatherViewModel(private val contract: WeatherViewContract, private val apiService: WeatherApiService) {

    fun getWeather() {
        apiService.getWeather(CITY_CODE_TOKYO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { weather, _ -> weather?.let { contract.setWeather(it) } }
    }

    fun onRefresh() { getWeather() }
}
