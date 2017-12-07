package com.knz21.tokyoweather.viewmodel

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.knz21.tokyoweather.model.Forecast

class ForecastViewModel(val forecast: Forecast) {
    val dateText: String get() = forecast.date.split("-").let { "${it[0]}年${it[1]}月${it[2]}日" }
    val max: String get() = "最高気温: ${forecast.temperature.max.text()}"
    val min: String get() = "最低気温: ${forecast.temperature.min.text()}"

    private fun Forecast.Temperature.Temp?.text(): String = this?.let { "${it.celsius}℃" } ?: "---"
}

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Glide.with(context).load(url).into(this)
}
