package com.tiki.challenge

import android.app.Application

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        mAppExecutors = AppExecutors()
    }

    companion object {
        private var mAppExecutors: AppExecutors? = null
        var instance: MainApplication? = null
    }
}