package com.application.localvideo.viewmodel.splash

import android.app.Application
import androidx.lifecycle.liveData
import com.application.localvideo.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class SplashViewModel(application: Application) : BaseViewModel(application) {

    val splashData = liveData(Dispatchers.IO) {
        delay(1000)
        emit(true)
    }
}