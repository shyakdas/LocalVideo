package com.application.localvideo.model

import java.io.File

class VideoModel(
    videoPath: File
) {

    var videoName: String? = videoPath.name

    var videoUri: String? = videoPath.absolutePath

    var url: String? = videoPath.absolutePath

}