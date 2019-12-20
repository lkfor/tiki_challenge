package com.tiki.challenge.api

interface DataListener {
    fun onError(message: String?)
    fun onSuccess(values: List<String>)
    fun onStartCallApi(loading: Boolean)
    fun onTerminateApi(loading: Boolean)
}