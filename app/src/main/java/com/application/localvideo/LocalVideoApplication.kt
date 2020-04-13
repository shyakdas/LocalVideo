package com.application.localvideo

import android.app.Application
import android.content.Context
import com.application.localvideo.database.LocalVideoDatabase

class LocalVideoApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: LocalVideoApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        LocalVideoDatabase.getDatabase(applicationContext)
    }
}