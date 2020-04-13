package com.application.localvideo.utils

import android.content.Context
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector

class CustomExoPlayer(private var context: Context) {

    private var player: SimpleExoPlayer? = null

    companion object {
        @Volatile
        var INSTANCE: CustomExoPlayer? = null

        fun getInstance(context: Context): CustomExoPlayer {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = CustomExoPlayer(context)
                INSTANCE = instance
                return instance
            }
        }
    }

    fun initExoPlayer(): SimpleExoPlayer? {
        if (player != null) {
            releaseVideoPlayer(player)
        }
        player = ExoPlayerFactory.newSimpleInstance(
            context, DefaultRenderersFactory(context), DefaultTrackSelector(),
            DefaultLoadControl()
        )
        return player
    }

    fun releaseVideoPlayer(player: SimpleExoPlayer?) {
        player?.release()
    }
}