package com.tiki.challenge.api

interface DataListener{
    fun onError(message: String?)
    fun onSuccess(value: Any?)
    fun onStartCallApi(loading: Boolean)
    fun onTerminateApi(loading: Boolean)
}