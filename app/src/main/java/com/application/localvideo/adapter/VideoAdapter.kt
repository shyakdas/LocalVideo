package com.application.localvideo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.localvideo.R
import com.application.localvideo.databinding.ItemVideoBinding
import com.application.localvideo.listener.VideoClickListener
import com.application.localvideo.model.VideoModel
import com.application.localvideo.utils.PlayerStateCallback
import com.application.localvideo.videoholder.VideoItemViewHolder
import com.google.android.exoplayer2.Player

class VideoAdapter(var videoList: ArrayList<VideoModel>, var listener: VideoClickListener) :
    RecyclerView.Adapter<VideoItemViewHolder>(), PlayerStateCallback {
    private var url: String? = null
    private lateinit var callback: PlayerStateCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemViewHolder {
        val listItemTextImageBinding: ItemVideoBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.item_video, parent, false)

        val holder =
            VideoItemViewHolder(listItemTextImageBinding.root, listItemTextImageBinding, listener)
        holder.itemRowBinding.listener = listener
        return holder
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoItemViewHolder, position: Int) {
        val videoModel = videoList[position]
        with(holder.itemRowBinding) {
            this.model = videoModel
            url = videoModel.videoUri
            callback = this@VideoAdapter
            executePendingBindings()
        }
    }

    override fun onVideoDurationRetrieved(duration: Long, player: Player) {

    }

    override fun onVideoBuffering(player: Player) {
    }

    override fun onStartedPlaying(player: Player) {
    }

    override fun onFinishedPlaying(player: Player) {
    }
}