package com.geektech.mangaread.core.extensions

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.geektech.mangaread.R

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.default_manga_image)
        .into(this)
}