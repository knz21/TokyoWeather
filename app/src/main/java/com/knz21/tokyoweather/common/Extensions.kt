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

@BindingAdapter("colors")
fun SwipeRefreshLayout.setColors(@ColorRes vararg colorResIds: Int) {
    setColorSchemeResources(*colorResIds)
}

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.setRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener) {
    setOnRefreshListener(listener)
}

fun String.formattedDateTime(): String = split("T").run {
    "${getOrNull(0)?.run { formattedDate() } ?: ""} ${getOrNull(1)?.run { formattedTime() } ?: ""}"
}

fun String.formattedDate(): String = split("-").let { "${it[0]}年${it[1]}月${it[2]}日" }

fun String.formattedTime(): String = split(":").let { "${it[0]}時${it[1]}分" }

fun String.announced(): String = "${this} 発表"

fun String.formattedDescription(): String = replace("\n", "").replace("【", "\n\n【").replace("】", "】\n")