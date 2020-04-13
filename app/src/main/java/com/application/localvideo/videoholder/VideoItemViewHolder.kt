package com.application.localvideo.videoholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.application.localvideo.databinding.ItemVideoBinding
import com.application.localvideo.listener.VideoClickListener

class VideoItemViewHolder(itemView: View, binding: ItemVideoBinding, listener: VideoClickListener) :
    RecyclerView.ViewHolder(itemView) {
    var itemRowBinding: ItemVideoBinding = binding
    lateinit var url: String
}