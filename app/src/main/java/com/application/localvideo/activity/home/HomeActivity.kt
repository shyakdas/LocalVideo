package com.application.localvideo.activity.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.application.localvideo.R
import com.application.localvideo.base.BaseActivity
import com.application.localvideo.databinding.ActivityHomeBinding
import com.application.localvideo.fragments.bookmark.BookMarkFragment
import com.application.localvideo.fragments.localvideo.LocalVideoFragment
import com.application.localvideo.utils.CustomExoPlayer
import com.application.localvideo.viewmodel.home.HomeViewModel
import com.application.localvideo.viewmodel.video.VideoViewModel


class HomeActivity : BaseActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var videoViewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initDataBinding()
        initLocalVideo()
        initBookMarkVideo()
        homeViewModel.mutableHomeData.value = true
    }

    private fun initDataBinding() {
        homeBinding = DataBindingUtil.setContentView(this, getLayOutId());
        homeBinding.viewmodel = homeViewModel
    }

    override fun getLayOutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel() {
        homeViewModel =
            ViewModelProvider(this, defaultViewModelProviderFactory).get(HomeViewModel::class.java)
        videoViewModel =
            ViewModelProvider(this, defaultViewModelProviderFactory).get(VideoViewModel::class.java)
    }

    private fun initLocalVideo() {
        homeViewModel.mutableHomeData.observe(this, Observer {
            if (it == true) {
                navigateToLocalVideo()
            }
        })
    }

    private fun navigateToLocalVideo() {
        val fm = supportFragmentManager
        val fragment = LocalVideoFragment()
        fm.beginTransaction().add(R.id.frame_container, fragment).commitAllowingStateLoss()
    }

    private fun initBookMarkVideo() {
        homeViewModel.mutableBookMarkData.observe(this, Observer {
            if (it == true) navigateToBookMarkVideo()
        })
    }

    private fun navigateToBookMarkVideo() {
        releasePlayer()
        val fm = supportFragmentManager
        val fragment = BookMarkFragment()
        fm.beginTransaction().add(R.id.frame_container, fragment).commitAllowingStateLoss()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    private fun releasePlayer() {
        CustomExoPlayer.getInstance(this).player?.release()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        videoViewModel.getListOfVideos()
    }
}