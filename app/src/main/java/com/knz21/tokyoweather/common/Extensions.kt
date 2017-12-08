package com.knz21.tokyoweather.common

import android.databinding.BindingAdapter
import android.support.annotation.ColorRes
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.setRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener) {
    setOnRefreshListener(listener)
}

@BindingAdapter("colors")
fun SwipeRefreshLayout.setColors(@ColorRes vararg colorResIds: Int) {
    setColorSchemeResources(*colorResIds)
}

fun String.formattedDescription(): String = replace("\n", "").replace("【", "\n\n【").replace("】", "】\n")