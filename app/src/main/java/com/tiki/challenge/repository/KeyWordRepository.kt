package com.tiki.challenge.repository

import android.annotation.SuppressLint
import com.tiki.challenge.api.DataListener
import com.tiki.challenge.api.KeyWordApi
import com.tiki.challenge.api.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class KeyWordRepository {
    private var factory: ServiceFactory = ServiceFactory()
    private var api: KeyWordApi =
        factory.provideSearchApi()

    @SuppressLint("CheckResult")
    fun getKeyWords(dataListener: DataListener) {
        api.getKeyWords()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { dataListener.onStartCallApi(true) }
            .doAfterTerminate { dataListener.onTerminateApi(false) }
            .subscribe({ result ->
                dataListener.onTerminateApi(false)
                dataListener.onSuccess(result)
            }, { throwable ->
                dataListener.onTerminateApi(false)
                dataListener.onError(throwable.localizedMessage)
            })
    }
}