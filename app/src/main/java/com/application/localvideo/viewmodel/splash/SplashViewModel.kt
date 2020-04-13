package com.application.localvideo.viewmodel.splash

import android.app.Application
import androidx.lifecycle.liveData
import com.application.localvideo.base.BaseViewModel
import com.application.localvideo.utils.MethodLoadDirectory
import com.application.localvideo.utils.StorageUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import java.io.File

class SplashViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var storage: File
    private lateinit var storagePaths: Array<String>

    val splashData = liveData(Dispatchers.IO) {
        delay(500)
        loadStorage()
        emit(true)
    }

    private fun loadStorage() {
        storagePaths = StorageUtils.getStorageDirectories(getApplication())

        for (path in storagePaths) {
            storage = File(path)
            MethodLoadDirectory.load_Directory_Files(storage)
        }
    }
}