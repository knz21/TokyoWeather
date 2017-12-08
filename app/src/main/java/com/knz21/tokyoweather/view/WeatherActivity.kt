package com.knz21.tokyoweather.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.knz21.tokyoweather.R
import com.knz21.tokyoweather.api.WeatherApiService
import com.knz21.tokyoweather.contract.WeatherViewContract
import com.knz21.tokyoweather.databinding.ActivityWeatherBinding
import com.knz21.tokyoweather.di.ApplicationModule
import com.knz21.tokyoweather.di.DaggerApplicationComponent
import com.knz21.tokyoweather.model.Forecast
import com.knz21.tokyoweather.model.Weather
import com.knz21.tokyoweather.viewmodel.WeatherViewModel
import javax.inject.Inject

class WeatherActivity : AppCompatActivity(), WeatherViewContract {
    @Inject lateinit var weatherApiService: WeatherApiService
    private val binding by lazy { DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule()).build().inject(this)
        setContentView(R.layout.activity_weather)
        binding.viewModel = WeatherViewModel(this, weatherApiService).apply { getWeather() }
    }

    override fun setWeather(weather: Weather) {
        binding.refresh.isRefreshing = false
        setForecasts(weather.forecasts)
    }

    private fun setForecasts(forecasts: List<Forecast>) {
        forecasts.getOrNull(0)?.let { binding.forecastToday.setForecast(it) }
        forecasts.getOrNull(1)?.let { binding.forecastTomorrow.setForecast(it) }
        forecasts.getOrNull(2)?.let { binding.forecastDayAfterTomorrow.setForecast(it) }
    }
}
