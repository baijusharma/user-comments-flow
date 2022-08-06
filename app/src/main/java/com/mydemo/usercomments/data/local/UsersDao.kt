package com.mydemo.usercomments.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mydemo.usercomments.data.model.CommentsResponseItem
import com.mydemo.usercomments.data.model.PostResponseItem

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postEntity: PostResponseItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(commentEntity: CommentsResponseItem): Long

}