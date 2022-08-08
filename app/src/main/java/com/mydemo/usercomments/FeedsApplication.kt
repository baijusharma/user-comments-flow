package com.mydemo.usercomments

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FeedsApplication : Application() {

    companion object {
        var appContext: FeedsApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}