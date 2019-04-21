package com.motonaka.googlebookssample

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun ImageView.setLoadImage(url: String) {
    Glide.with(this).load(url).into(this)
}