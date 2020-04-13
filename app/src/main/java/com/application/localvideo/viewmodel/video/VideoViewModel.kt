package com.application.localvideo.viewmodel.video

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.localvideo.base.BaseViewModel
import com.application.localvideo.database.LocalVideoDatabase
import com.application.localvideo.model.VideoModel
import com.application.localvideo.repository.BookMarkRepository
import com.application.localvideo.utils.VideoConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class VideoViewModel(application: Application) : BaseViewModel(application) {
    var mutableVideoList = MutableLiveData<List<VideoModel>>()
    var mutableBookMarkVideoList = MutableLiveData<List<VideoModel>>()
    var videoEmptyStateVisibility = ObservableField(false)
    var bookMarkEmptyStateVisibility = ObservableField(false)
    val dao = LocalVideoDatabase.getDatabase(application).bookMarkDao()
    private var repository: BookMarkRepository? = BookMarkRepository.getInstance(dao)

    fun getListOfVideos() {
        val mList = VideoConstant.allMediaList
        val videoList = ArrayList<VideoModel>()
        viewModelScope.launch(Dispatchers.IO) {
            mList.forEach {
                videoList.add(VideoModel(it))
            }
            withContext(Dispatchers.Main) {
                if (videoList.isNullOrEmpty())
                    videoEmptyStateVisibility.set(true)
                else
                    videoEmptyStateVisibility.set(false)
                mutableVideoList.value = videoList
            }
        }
    }

    fun saveBookMark(videoModel: VideoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository?.insert(videoModel)
            withContext(Dispatchers.Main){
                getBookMarkVideos()
            }
        }
    }

    fun getBookMarkVideos() {
        val videoList = ArrayList<VideoModel>()
        BookMarkRepository.getInstance(dao).allBookMarkVideos.observeForever {
            it.forEach {
                videoList.add(VideoModel(File(it.bookMarkVideos)))
            }
        }
        if (videoList.isNullOrEmpty())
            bookMarkEmptyStateVisibility.set(true)
        else
            bookMarkEmptyStateVisibility.set(false)
        mutableBookMarkVideoList.value = videoList
    }
}