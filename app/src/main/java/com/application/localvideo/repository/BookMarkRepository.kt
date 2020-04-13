package com.application.localvideo.repository

import androidx.lifecycle.LiveData
import com.application.localvideo.database.BookMarkDao
import com.application.localvideo.model.BookMarkModel

class BookMarkRepository(dao: BookMarkDao?) {

    companion object {
        @Volatile
        var INSTANCE: BookMarkRepository? = null

        fun getInstance(dao: BookMarkDao?): BookMarkRepository {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = BookMarkRepository(dao)
                INSTANCE = instance
                return instance
            }
        }
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allBookMarkVideos: LiveData<List<BookMarkModel>> = dao!!.getBookMarkVideosList()
}