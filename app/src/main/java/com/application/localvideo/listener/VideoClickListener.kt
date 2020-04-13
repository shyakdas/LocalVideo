package com.application.localvideo.listener

import com.application.localvideo.model.VideoModel

interface VideoClickListener {

    fun onItemClick(videoModel: VideoModel)
}