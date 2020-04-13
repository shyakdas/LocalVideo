package com.application.localvideo.videoholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.application.localvideo.databinding.ItemVideoBinding
import java.io.File

class VideoItemViewHolder(itemView: View, binding: ItemVideoBinding) :
    RecyclerView.ViewHolder(itemView) {

    var itemRowBinding: ItemVideoBinding = binding
    fun bind(file: File) {

    }
}