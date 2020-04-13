package com.application.localvideo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.application.localvideo.model.BookMarkModel

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(BookMarkModel::class), version = 1, exportSchema = false)
abstract class LocalVideoDatabase : RoomDatabase() {

    abstract fun bookMarkDao(): BookMarkDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LocalVideoDatabase? = null

        fun getDatabase(context: Context): LocalVideoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalVideoDatabase::class.java,
                    "local_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}