package com.tiki.challenge.ui.key_search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tiki.challenge.api.DataListener
import com.tiki.challenge.model.KeyWordModel
import com.tiki.challenge.repository.KeyWordRepository

class KeyWordViewModel(var repositoryListener: RepositoryListener?) : ViewModel() {
    interface RepositoryListener {
        fun onKeyWordsChange(keywords: List<KeyWordModel>)
    }

    private val searchRepository = KeyWordRepository()
    var isLoadingKeyWords: MutableLiveData<Boolean> = MutableLiveData(true)

    fun fetchKeyWords() {
        searchRepository.getKeyWords(object : DataListener {
            override fun onError(message: String?) {
                repositoryListener?.onKeyWordsChange(ArrayList())
            }

            override fun onSuccess(values: List<String>) {
                try {
                    val result = ArrayList<KeyWordModel>()
                    for (item in values) {
                        result.add(KeyWordModel(item))
                    }
                    repositoryListener?.onKeyWordsChange(result)
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