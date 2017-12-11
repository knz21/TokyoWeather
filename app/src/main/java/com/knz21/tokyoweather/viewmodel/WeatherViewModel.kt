package com.knz21.tokyoweather.viewmodel

import android.databinding.ObservableField
import android.view.View
import com.knz21.tokyoweather.R
import com.knz21.tokyoweather.api.WeatherApiService
import com.knz21.tokyoweather.common.announced
import com.knz21.tokyoweather.common.formattedDateTime
import com.knz21.tokyoweather.common.formattedDescription
import com.knz21.tokyoweather.contract.WeatherViewContract
import com.knz21.tokyoweather.model.Weather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val CITY_CODE_TOKYO = 130010

class WeatherViewModel(private val contract: WeatherViewContract, private val apiService: WeatherApiService) {
    val colors: IntArray = intArrayOf(R.color.colorPrimary, R.color.colorAccent)
    val time: ObservableField<String> = ObservableField()
    val description: ObservableField<String> = ObservableField()
    val descTime: ObservableField<String> = ObservableField()
    val contentVisibility: ObservableField<Int> = ObservableField(View.GONE)
    val progressVisibility: ObservableField<Int> = ObservableField(View.VISIBLE)
    val reloadVisibility: ObservableField<Int> = ObservableField(View.GONE)

    fun load() {
        apiService.getWeather(CITY_CODE_TOKYO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { weather, _ -> weather?.let { setWeather(it) } ?: onError() }
    }

    private fun setWeather(weather: Weather) {
        time.set(weather.publicTime.formattedDateTime().announced())
        description.set(weather.description.text.formattedDescription())
        descTime.set(weather.description.publicTime.formattedDateTime().announced())
        contentVisibility.set(View.VISIBLE)
        progressVisibility.set(View.GONE)
        contract.setWeather(weather)
        contract.removeRefreshing()
    }

    private fun onError() {
        contract.removeRefreshing()
        if (contentVisibility.get() != View.VISIBLE) setReload()
    }

    private fun setReload() {
        progressVisibility.set(View.GONE)
        reloadVisibility.set(View.VISIBLE)
    }

    fun reload(v: View) {
        progressVisibility.set(View.VISIBLE)
        reloadVisibility.set(View.GONE)
        load()
    }
}
