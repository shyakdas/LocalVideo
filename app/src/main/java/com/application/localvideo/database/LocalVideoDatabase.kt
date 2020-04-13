package com.application.localvideo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.application.localvideo.model.BookMarkModel

@Database(
    entities = [(BookMarkModel::class)],
    version = 1, exportSchema = false
)
abstract class LocalVideoDatabase : RoomDatabase() {

    abstract fun bookMarkDao(): BookMarkDao

    companion object {
        var INSTANCE: LocalVideoDatabase? = null

        fun getAppDatabase(context: Context): LocalVideoDatabase? {
            if (INSTANCE == null) {
                synchronized(LocalVideoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LocalVideoDatabase::class.java,
                        "local_video_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}