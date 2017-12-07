package com.knz21.tokyoweather.model

data class Forecast(val date: String, val dateLabel: String, val telop: String,
                    val image: Image, val temperature: Temperature) {
    data class Image(val url: String)
    data class Temperature(val max: Temp?, val min: Temp) {
        data class Temp(val celsius: String)
    }
}
