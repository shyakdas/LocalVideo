package com.application.localvideo.videoholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.application.localvideo.databinding.ItemVideoBinding
import com.application.localvideo.model.VideoModel

class VideoItemViewHolder(itemView: View, binding: ItemVideoBinding) :
    RecyclerView.ViewHolder(itemView) {
    private var itemRowBinding: ItemVideoBinding = binding

    fun bind(videoModel: VideoModel) {
        itemRowBinding.model = videoModel
        itemRowBinding.executePendingBindings()
    }
}