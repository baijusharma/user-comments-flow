package com.mydemo.usercomments.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.data.model.PostItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class UsersDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: FeedsDatabase
    private lateinit var usersDao: UsersDao

    private val job = Job()
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(job + testDispatcher)

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FeedsDatabase::class.java
        ).allowMainThreadQueries().build()
        usersDao = database.getUsersDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertPost() = testScope.runTest {
        val postItemList = ArrayList<PostItem>()
        postItemList.add(PostItem(1,"Test","Test with coroutine", 54 ))
        usersDao.upsertPost(postItemList)

        val userPostItem = usersDao.getAllPost()

        assertThat(postItemList).containsExactlyElementsIn(userPostItem)
    }


    @Test
    fun getComments() = testScope.runTest {
        val postItemList = ArrayList<PostItem>()
        postItemList.add(PostItem(1,"Test","Test with coroutine", 54 ))
        val commentsItemList = ArrayList<CommentsItem>()
        commentsItemList.add(CommentsItem("Test",1,2,"Test Comments", "sharmab@gmail.com"))
        usersDao.upsertComments(commentsItemList)

        val userCommentItem = usersDao.getAllComments(1)
        assertThat(userCommentItem).isNotNull()
    }

    @Test
    fun commentNotAvailable() = testScope.runTest {
        val postItemList = ArrayList<PostItem>()
        postItemList.add(PostItem(1,"Test","Test with coroutine", 54 ))
        val commentsItemList = ArrayList<CommentsItem>()
        commentsItemList.add(CommentsItem("Test",2,5,"Test Comments", "test@gmail.com"))
        usersDao.upsertComments(commentsItemList)

        val userCommentItem = usersDao.getAllComments(1)
        assertThat(userCommentItem).isNotEqualTo(commentsItemList)
    }


}