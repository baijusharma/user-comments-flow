package com.mydemo.usercomments.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.data.model.PostItem

@Database(entities = [PostItem::class, CommentsItem::class], version = 1, exportSchema = false)
abstract class FeedsDatabase: RoomDatabase()  {
    abstract val usersDao: UsersDao
}