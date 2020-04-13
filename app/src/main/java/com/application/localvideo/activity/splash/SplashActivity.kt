package com.application.localvideo.activity.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.application.localvideo.R
import com.application.localvideo.activity.home.HomeActivity
import com.application.localvideo.base.BaseActivity
import com.application.localvideo.viewmodel.splash.SplashViewModel


class SplashActivity : BaseActivity() {
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViewModel()
        initSplashData()
    }

    override fun getLayOutId(): Int {
        return R.layout.activity_splash
    }

    override fun initViewModel() {
        splashViewModel =
            ViewModelProvider(this, defaultViewModelProviderFactory).get(
                SplashViewModel::class.java
            )
    }

    private fun initSplashData() {
        splashViewModel.splashData.observe(this, Observer {
            if (it == true) navigateToHomeActivity()
        })
    }

    private fun navigateToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}