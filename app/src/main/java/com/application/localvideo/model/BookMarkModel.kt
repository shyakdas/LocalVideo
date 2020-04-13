package com.application.localvideo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_videos")
class BookMarkModel(
    @PrimaryKey
    @ColumnInfo(name = "bookMarkVideos")
    var bookMarkVideos: String?
)