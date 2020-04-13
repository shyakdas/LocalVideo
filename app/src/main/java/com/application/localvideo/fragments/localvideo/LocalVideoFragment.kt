package com.application.localvideo.fragments.localvideo

import android.util.Log
import android.view.View
import com.application.localvideo.R
import com.application.localvideo.base.BaseFragment
import com.application.localvideo.utils.VideoConstant

class LocalVideoFragment : BaseFragment() {

    override fun getLayout(): Int {
        return R.layout.fragment_local_videos
    }

    override fun initView(view: View) {
        VideoConstant.allMediaList
        Log.e("TAG", "Videolist" + VideoConstant.allMediaList.size)
    }
}