package com.tiki.challenge.api

class ApiRepository {
    companion object {
        private var sInstance: ApiRepository? = null

        fun getInstance(): ApiRepository {
            if (sInstance == null) {
                synchronized(ApiRepository::class.java) {
                    if (sInstance == null) {
                        sInstance = ApiRepository()
                    }
                }
            }
            return sInstance!!
        }
    }
}