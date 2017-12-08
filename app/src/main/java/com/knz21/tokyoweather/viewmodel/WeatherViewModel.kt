package com.knz21.tokyoweather.viewmodel

import android.databinding.ObservableField
import com.knz21.tokyoweather.R
import com.knz21.tokyoweather.api.WeatherApiService
import com.knz21.tokyoweather.common.announced
import com.knz21.tokyoweather.common.formattedDateTime
import com.knz21.tokyoweather.common.formattedDescription
import com.knz21.tokyoweather.contract.WeatherViewContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val CITY_CODE_TOKYO = 130010

class WeatherViewModel(private val contract: WeatherViewContract, private val apiService: WeatherApiService) {
    val colors: IntArray = intArrayOf(R.color.colorPrimary, R.color.colorAccent)
    val time: ObservableField<String> = ObservableField()
    val description: ObservableField<String> = ObservableField()
    val descTime: ObservableField<String> = ObservableField()

    fun getWeather() {
        apiService.getWeather(CITY_CODE_TOKYO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { weather, _ ->
                    weather?.let {
                        contract.setWeather(it)
                        time.set(it.publicTime.formattedDateTime().announced())
                        description.set(it.description.text.formattedDescription())
                        descTime.set(it.description.publicTime.formattedDateTime().announced())
                    }
                }
    }
}
