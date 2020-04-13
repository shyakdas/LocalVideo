package com.application.localvideo.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.application.localvideo.model.BookMarkModel

interface BookMarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookMark(bookMarkModel: BookMarkModel)

    @Query("SELECT * FROM bookmark_videos")
    fun getBookMarkVideosList(): LiveData<List<BookMarkModel>>
}