package com.motonaka.googlebookssample.extenstion

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun ImageView.setLoadImage(url: String?) {
    url?.let {
        Glide.with(this).load(url).into(this)
    }
}

fun RecyclerView.addDefalutItemDecoration() {
    val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager(context).orientation)
    addItemDecoration(dividerItemDecoration)
}