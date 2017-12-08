package com.knz21.tokyoweather.viewmodel

import com.knz21.tokyoweather.common.formattedDate
import com.knz21.tokyoweather.model.Forecast

class ForecastViewModel(val forecast: Forecast) {
    val dateText: String get() = forecast.date.formattedDate()
    val max: String get() = "最高気温: ${forecast.temperature.max.text()}"
    val min: String get() = "最低気温: ${forecast.temperature.min.text()}"

    private fun Forecast.Temperature.Temp?.text(): String = this?.let { "${it.celsius}℃" } ?: "---"
}
