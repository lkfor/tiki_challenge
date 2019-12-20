package com.tiki.challenge

import android.app.Application
import com.tiki.challenge.api.ApiRepository

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        mAppExecutors = AppExecutors()
    }

    companion object {
        private var mAppExecutors: AppExecutors? = null
        var instance: MainApplication? = null
        private var sApiRepository: ApiRepository? = null

        fun getApiRepository(): ApiRepository {
            if (sApiRepository == null) {
                sApiRepository = ApiRepository.getInstance()
            }
            return sApiRepository!!
        }
    }
}