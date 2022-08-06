package com.mydemo.usercomments.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mydemo.usercomments.data.model.CommentsResponseItem
import com.mydemo.usercomments.data.model.PostResponseItem

@Database(entities = [PostResponseItem::class, CommentsResponseItem::class], version = 1, exportSchema = false)
abstract class FeedsDatabase: RoomDatabase()  {
    abstract val usersDao: UsersDao
}