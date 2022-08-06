package com.mydemo.usercomments.di

import com.mydemo.usercomments.network.ApiService
import com.mydemo.usercomments.network.repository.FeedsRepository
import com.mydemo.usercomments.network.repository.IFeeds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideApiRepository(apiService: ApiService) = FeedsRepository(apiService) as IFeeds

}