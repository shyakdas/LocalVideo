package com.application.localvideo

import android.app.Application
import android.content.Context

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
        // Todo
    }
}