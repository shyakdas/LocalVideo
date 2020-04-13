package com.application.localvideo.viewmodel.video

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.localvideo.base.BaseViewModel
import com.application.localvideo.model.VideoModel
import com.application.localvideo.utils.VideoConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideoViewModel(application: Application) : BaseViewModel(application) {
    var mutableVideoList = MutableLiveData<List<VideoModel>>()
    var emptyStateVisibility = ObservableField(false)

    fun getListOfVideos() {
        val mList = VideoConstant.allMediaList
        val videoList = ArrayList<VideoModel>()
        viewModelScope.launch(Dispatchers.IO) {
            mList.forEach {
                videoList.add(VideoModel(it.absolutePath))
            }
            withContext(Dispatchers.Main) {
                if (videoList.isNullOrEmpty())
                    emptyStateVisibility.set(true)
                else
                    emptyStateVisibility.set(false)
                mutableVideoList.value = videoList
            }
        }
    }
}