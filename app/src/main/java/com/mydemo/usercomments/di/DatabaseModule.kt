package com.mydemo.usercomments.di

import android.content.Context
import androidx.room.Room
import com.mydemo.usercomments.data.local.FeedsDatabase
import com.mydemo.usercomments.data.local.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): FeedsDatabase {
        return Room.databaseBuilder(
            appContext,
            FeedsDatabase::class.java,
            FeedsDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserDao(feedsDatabase: FeedsDatabase): UsersDao {
        return feedsDatabase.getUsersDao()
    }
}