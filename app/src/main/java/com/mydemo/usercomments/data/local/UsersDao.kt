package com.mydemo.usercomments.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.data.model.PostItem
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertPost(postEntity: List<PostItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertComments(commentEntity: List<CommentsItem>)

    @Query("SELECT * FROM post")
    fun getAllPost(): List<PostItem>

    @Query("SELECT * FROM comments")
    fun getAllComments(): List<CommentsItem>

    @Query("SELECT * FROM post WHERE body LIKE :searchQuery OR title LIKE :searchQuery")
    fun searchPostDatabase(searchQuery: String): List<PostItem>

    @Query("SELECT * FROM comments WHERE body LIKE :searchQuery OR email LIKE :searchQuery")
    fun searchCommentsDatabase(searchQuery: String): List<CommentsItem>
}