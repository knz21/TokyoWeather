package com.knz21.tokyoweather.common

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.onRefresh(listener: SwipeRefreshLayout.OnRefreshListener) {
    setOnRefreshListener(listener)
}
