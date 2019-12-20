package com.tiki.challenge.api

import io.reactivex.Observable
import retrofit2.http.GET


interface SearchApi {
    companion object {
        private const val KEYWORDS = "keywords.json"
        private const val REQUESTS = "requests/"
    }

    @GET(KEYWORDS)
    fun getKeyWords(): Observable<List<String>>

}