package com.application.localvideo.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private var isOnBackPressed: MutableLiveData<Boolean>? = null

    init {
        isOnBackPressed = MutableLiveData()
    }

    private fun cancelAllRequests() = viewModelScope.coroutineContext.cancel()

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }
}