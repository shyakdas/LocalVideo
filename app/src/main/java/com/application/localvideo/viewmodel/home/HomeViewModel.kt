package com.application.localvideo.viewmodel.home

import android.app.Application
import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import com.application.localvideo.R
import com.application.localvideo.base.BaseViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeViewModel(application: Application) : BaseViewModel(application),
    BottomNavigationView.OnNavigationItemSelectedListener {

    var mutableHomeData = MutableLiveData<Boolean>()
    var mutableBookMarkData = MutableLiveData<Boolean>()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.local_videos -> {
                mutableHomeData.value = true
                mutableBookMarkData.value = false;
                return true
            }

            R.id.bookmark -> {
                mutableBookMarkData.value = true;
                mutableHomeData.value = false
                return true
            }
        }
        return false
    }
}