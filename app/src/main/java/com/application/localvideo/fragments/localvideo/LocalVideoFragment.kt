package com.application.localvideo.fragments.localvideo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.localvideo.R
import com.application.localvideo.adapter.VideoAdapter
import com.application.localvideo.base.BaseFragment
import com.application.localvideo.databinding.FragmentLocalVideosBinding
import com.application.localvideo.model.VideoModel
import com.application.localvideo.viewmodel.video.VideoViewModel
import kotlinx.android.synthetic.main.fragment_local_videos.*

class LocalVideoFragment : BaseFragment() {
    private lateinit var videoVideoModel: VideoViewModel
    private var videoList = ArrayList<VideoModel>()
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var dataBinding: FragmentLocalVideosBinding

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        dataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        dataBinding.videoViewModel = videoVideoModel
        return dataBinding.root
    }

    override fun getLayout(): Int {
        return R.layout.fragment_local_videos
    }

    override fun initView(view: View) {
        initData()
        initVideoList()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = layoutManager
        videoAdapter = VideoAdapter(videoList = videoList)
        recycler_view.adapter = videoAdapter
    }

    private fun initData() {
        videoVideoModel.getListOfVideos()
    }

    override fun initViewModel() {
        videoVideoModel =
            ViewModelProvider(this, defaultViewModelProviderFactory).get(VideoViewModel::class.java)
    }

    private fun initVideoList() {
        videoVideoModel.mutableVideoList.observe(this, Observer {
            videoList.clear()
            videoList.addAll(it)
            videoAdapter.notifyDataSetChanged()
        })
    }
}