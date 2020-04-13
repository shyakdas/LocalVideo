package com.application.localvideo.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File


@BindingAdapter("onNavigationItemSelected")
fun setOnNavigationItemSelected(
    view: BottomNavigationView, listener: BottomNavigationView.OnNavigationItemSelectedListener?
) {
    view.setOnNavigationItemSelectedListener(listener)
}

@BindingAdapter("app:goneVisible")
fun goneVisible(view: View, visibility: Boolean) {
    if (visibility) view.visibility = View.VISIBLE else view.visibility = View.GONE
}

@BindingAdapter("app:loadImage")
fun loadImage(imageView: ImageView, imageURL: String) {
    Glide.with(imageView.context)
        .load(Uri.fromFile(File(imageURL))).thumbnail(0.1f).into(imageView)
}