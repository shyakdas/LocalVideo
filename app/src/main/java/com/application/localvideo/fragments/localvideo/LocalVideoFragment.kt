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
import com.application.localvideo.listener.VideoClickListener
import com.application.localvideo.model.VideoModel
import com.application.localvideo.utils.StartSnapHelper
import com.application.localvideo.viewmodel.video.VideoViewModel
import kotlinx.android.synthetic.main.fragment_local_videos.*


open class LocalVideoFragment : BaseFragment(), VideoClickListener {
    protected lateinit var videoViewModel: VideoViewModel
    private var videoList = ArrayList<VideoModel>()
    protected lateinit var videoAdapter: VideoAdapter
    private lateinit var dataBinding: FragmentLocalVideosBinding

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        dataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        dataBinding.videoViewModel = videoViewModel
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
        val snapHelpers = StartSnapHelper()
        snapHelpers.attachToRecyclerView(recycler_view)
        recycler_view.layoutManager = layoutManager
        initAdapterData()
    }

    open fun initAdapterData() {
        videoAdapter = VideoAdapter(videoList = videoList, listener = this)
        recycler_view.adapter = videoAdapter
    }

    open fun initData() {
        videoViewModel.getListOfVideos()
    }

    override fun initViewModel() {
        activity?.let {
            videoViewModel = ViewModelProvider(
                it,
                defaultViewModelProviderFactory
            ).get(VideoViewModel::class.java)
        }
    }

    open fun initVideoList() {
        videoViewModel.mutableVideoList.observe(this, Observer {
            videoList.clear()
            videoList.addAll(it)
            videoAdapter.notifyDataSetChanged()
        })
    }

    override fun onItemClick(videoModel: VideoModel) {
        videoViewModel.saveBookMark(videoModel)
    }
}