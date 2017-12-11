package com.knz21.tokyoweather.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.knz21.tokyoweather.R
import com.knz21.tokyoweather.databinding.ViewForecastBinding
import com.knz21.tokyoweather.model.Forecast
import com.knz21.tokyoweather.viewmodel.ForecastViewModel

class ForecastView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                             defStyleAttr: Int = 0, defStyleRes: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val binding: ViewForecastBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_forecast, this, true)

    fun setForecast(forecast: Forecast) {
        binding.viewModel = ForecastViewModel(forecast)
    }
}
