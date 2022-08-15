package com.mydemo.usercomments.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.data.model.PostItem

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsertPost(postEntity: List<PostItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsertComments(commentEntity: List<CommentsItem>)

    @Query("SELECT * FROM post")
    fun getAllPost(): List<PostItem>

    @Query("SELECT * FROM comments WHERE postId =:postId")
    fun getAllComments(postId: Int): List<CommentsItem>

    @Query("SELECT * FROM post WHERE body LIKE :searchQuery OR title LIKE :searchQuery")
    fun searchPostDatabase(searchQuery: String): List<PostItem>

    @Query("SELECT * FROM comments WHERE body LIKE :searchQuery OR email LIKE :searchQuery")
    fun searchCommentsDatabase(searchQuery: String): List<CommentsItem>
}