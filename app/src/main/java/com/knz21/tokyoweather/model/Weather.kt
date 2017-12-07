package com.knz21.tokyoweather.model

data class Weather(val forecasts: List<Forecast>, val description: Description, val publicTime: String) {
    data class Description(val text: String, val publicTime: String)
}
