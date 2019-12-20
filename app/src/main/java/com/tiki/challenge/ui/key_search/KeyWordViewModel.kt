package com.tiki.challenge.ui.key_search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tiki.challenge.api.DataListener
import com.tiki.challenge.model.KeySearchModel
import com.tiki.challenge.repository.SearchRepository

class KeyWordViewModel(var repositoryListener: RepositoryListener?) : ViewModel() {
    interface RepositoryListener {
        fun onKeyWordsChange(keywords: List<KeySearchModel>)
    }

    private val searchRepository = SearchRepository()
    var isLoadingKeyWords: MutableLiveData<Boolean> = MutableLiveData(true)

    fun fetchKeyWords() {
        searchRepository.getKeyWords(object : DataListener {
            override fun onError(message: String?) {
                repositoryListener?.onKeyWordsChange(ArrayList())
            }

            override fun onSuccess(value: Any?) {
                try {
                    val data: List<String> = value as List<String>
                    val result = ArrayList<KeySearchModel>()
                    for (item in data) {
                        result.add(KeySearchModel(item))
                    }
                    repositoryListener?.onKeyWordsChange(ArrayList())
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    repositoryListener?.onKeyWordsChange(ArrayList())
                }
            }

            override fun onStartCallApi(loading: Boolean) {
                isLoadingKeyWords.value = true
            }

            override fun onTerminateApi(loading: Boolean) {
                isLoadingKeyWords.value = false
            }

        })
    }
}