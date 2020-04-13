package com.application.localvideo.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

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