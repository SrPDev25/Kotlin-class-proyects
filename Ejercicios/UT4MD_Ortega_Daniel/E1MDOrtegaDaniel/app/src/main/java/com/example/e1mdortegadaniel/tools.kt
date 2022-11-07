package com.example.e1mdortegadaniel

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageUrl")
fun loadImage(view: View,
              imageUrl: String?) {
    val image: ImageView = view as ImageView
    Glide.with(image.context)
        .load(imageUrl)
        .into(image)
}