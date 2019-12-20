package com.tiki.challenge.ui.key_search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tiki.challenge.R
import com.tiki.challenge.databinding.ActivityKeySearchBinding
import com.tiki.challenge.model.KeySearchModel
import com.tiki.challenge.utils.SpacingDecoration

class KeySearchActivity() : AppCompatActivity(), KeyWordViewModel.RepositoryListener {
    override fun onKeyWordsChange(keywords: List<KeySearchModel>) {
        this.keywords.clear()
        this.keywords.addAll(keywords)
        keyWordAdapter.notifyDataSetChanged()
        binding.tvEmptyData.visibility = if(this.keywords.isNullOrEmpty()) View.VISIBLE else View.GONE
    }

    private lateinit var keyWordViewModel: KeyWordViewModel
    private lateinit var binding: ActivityKeySearchBinding

    private lateinit var keyWordAdapter: KeyWordAdapter
    private val keywords = ArrayList<KeySearchModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_key_search)
        keyWordViewModel = KeyWordViewModel(this)
        binding.viewModel = keyWordViewModel
        binding.lifecycleOwner = this
        initView()
    }


    override fun onResume() {
        super.onResume()
        initData()
    }

    private fun initView() {
        keyWordAdapter = KeyWordAdapter(keywords)
        val itemSpacing = resources.getDimensionPixelOffset(R.dimen.margin)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL
        binding.rvKeyWords.layoutManager = linearLayoutManager
        binding.rvKeyWords.addItemDecoration(
            SpacingDecoration(
                itemSpacing,
                SpacingDecoration.HORIZONTAL,
                false,
                false
            )
        )
        binding.rvKeyWords.adapter = keyWordAdapter
        keyWordViewModel.isLoadingKeyWords.observe(this, Observer { loading ->
            binding.pbKeyWordLoading.visibility = if (loading) View.VISIBLE else View.GONE
        })
    }


    private fun initData() {
        keyWordViewModel.fetchKeyWords()
    }


}
