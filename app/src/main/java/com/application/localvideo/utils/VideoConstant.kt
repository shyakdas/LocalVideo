package com.application.localvideo.utils

import java.io.File

class VideoConstant {

    companion object {
        var videoExtensions = arrayOf(
            ".mp4"
        )

        //all loaded files will be here
        var allMediaList: ArrayList<File> = ArrayList()
    }
}