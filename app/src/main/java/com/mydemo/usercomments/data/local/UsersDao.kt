package com.mydemo.usercomments.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.data.model.PostItem

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postEntity: PostItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(commentEntity: CommentsItem): Long

}