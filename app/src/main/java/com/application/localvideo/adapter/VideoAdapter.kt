package com.application.localvideo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.localvideo.R
import com.application.localvideo.databinding.ItemVideoBinding
import com.application.localvideo.videoholder.VideoItemViewHolder
import java.io.File

class VideoAdapter(var videoList: ArrayList<File>) : RecyclerView.Adapter<VideoItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemViewHolder {
        val listItemTextImageBinding: ItemVideoBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.item_video, parent, false)

        val holder =
            VideoItemViewHolder(listItemTextImageBinding.videoView, listItemTextImageBinding)
        return holder
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoItemViewHolder, position: Int) {
    }
}