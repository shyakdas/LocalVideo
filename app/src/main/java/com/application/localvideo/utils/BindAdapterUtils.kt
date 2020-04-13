package com.application.localvideo.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File


@BindingAdapter("onNavigationItemSelected")
fun setOnNavigationItemSelected(
    view: BottomNavigationView, listener: BottomNavigationView.OnNavigationItemSelectedListener?
) {
    view.setOnNavigationItemSelectedListener(listener)
}

@BindingAdapter("app:goneVisible")
fun goneVisible(view: View, visibility: Boolean) {
    if (visibility) view.visibility = View.VISIBLE else view.visibility = View.GONE
}

@BindingAdapter("app:loadImage")
fun loadImage(imageView: ImageView, imageURL: String) {
    Glide.with(imageView.context)
        .load(Uri.fromFile(File(imageURL))).thumbnail(0.1f).into(imageView)
}

@BindingAdapter("video_url", "on_state_change")
fun PlayerView.loadVideo(url: String, callback: PlayerStateCallback) {
    val player = CustomExoPlayer.getInstance(context).initExoPlayer()
    player?.playWhenReady = true
    player?.repeatMode = Player.REPEAT_MODE_ALL
    // When changing track, retain the latest frame instead of showing a black screen
    setKeepContentOnPlayerReset(true)
    // We'll show the controller
    this.useController = true
    // Provide url to load the video from here
    val mediaSource = ExtractorMediaSource.Factory(
        DefaultDataSourceFactory(context, "Demo")
    )
        .createMediaSource(Uri.parse(url))

    player?.prepare(mediaSource)

    this.player = player

    this.player!!.addListener(object : Player.EventListener {

        override fun onPlayerError(error: ExoPlaybackException?) {
            super.onPlayerError(error)
        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            super.onPlayerStateChanged(playWhenReady, playbackState)

            if (playbackState == Player.STATE_BUFFERING) callback.onVideoBuffering(player!!) // Buffering.. set progress bar visible here
            if (playbackState == Player.STATE_READY) {
                // [PlayerView] has fetched the video duration so this is the block to hide the buffering progress bar
                callback.onVideoDurationRetrieved(this@loadVideo.player.duration, player!!)
            }
            if (playbackState == Player.STATE_READY && player!!.playWhenReady) {
                // [PlayerView] has started playing/resumed the video
                callback.onStartedPlaying(player)
            }
        }
    })
}