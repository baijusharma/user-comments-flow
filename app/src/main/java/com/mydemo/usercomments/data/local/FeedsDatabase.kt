package com.mydemo.usercomments.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.data.model.PostItem

@Database(entities = [PostItem::class, CommentsItem::class], version = 1, exportSchema = false)
abstract class FeedsDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDao

    companion object {
        const val DATABASE_NAME: String = "user_feeds"

        @Volatile
        private var instance: FeedsDatabase? = null

        fun getInstance(context: Context): FeedsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): FeedsDatabase {
            return Room.databaseBuilder(context, FeedsDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}