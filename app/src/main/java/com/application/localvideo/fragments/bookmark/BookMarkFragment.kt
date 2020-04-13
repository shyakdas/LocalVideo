package com.application.localvideo.fragments.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.application.localvideo.R
import com.application.localvideo.adapter.VideoAdapter
import com.application.localvideo.databinding.FragmentBookmarkBinding
import com.application.localvideo.fragments.localvideo.LocalVideoFragment
import com.application.localvideo.model.VideoModel
import kotlinx.android.synthetic.main.fragment_local_videos.*

class BookMarkFragment : LocalVideoFragment() {

    private lateinit var dataBinding: FragmentBookmarkBinding
    private var bookMarkVideoList = ArrayList<VideoModel>()

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        dataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        dataBinding.viewmodel = videoViewModel
        return dataBinding.root
    }

    override fun getLayout(): Int {
        return R.layout.fragment_bookmark
    }

    override fun initData() {
        videoViewModel.getBookMarkVideos()
    }

    override fun initVideoList() {
        videoViewModel.mutableBookMarkVideoList.observe(this, Observer {
            bookMarkVideoList.clear()
            bookMarkVideoList.addAll(it)
            videoAdapter.notifyDataSetChanged()
        })
    }

    override fun initAdapterData() {
        videoAdapter = VideoAdapter(videoList = bookMarkVideoList, listener = this)
        recycler_view.adapter = videoAdapter
    }
}