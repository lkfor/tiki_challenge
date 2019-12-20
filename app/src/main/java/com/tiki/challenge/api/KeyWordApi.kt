package com.tiki.challenge.api

import io.reactivex.Observable
import retrofit2.http.GET


interface KeyWordApi {
    companion object {
        private const val KEYWORDS = "keywords.json"
    }

    @GET(KEYWORDS)
    fun getKeyWords(): Observable<List<String>>

}