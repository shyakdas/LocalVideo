package com.application.localvideo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayOutId())
    }

    abstract fun getLayOutId(): Int

    open fun initViewModel() {
        // Subclass will override this method¬
    }
}
