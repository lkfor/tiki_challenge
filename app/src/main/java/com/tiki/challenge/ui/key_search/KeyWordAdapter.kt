package com.tiki.challenge.ui.key_search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tiki.challenge.R
import com.tiki.challenge.model.KeyWordModel
import com.tiki.challenge.utils.DrawableUtils
import com.tiki.challenge.utils.StringUtils
import kotlinx.android.synthetic.main.item_keyword.view.*

class KeyWordAdapter(private var keywords: List<KeyWordModel>) :
    RecyclerView.Adapter<KeyWordAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_keyword, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return keywords.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = keywords[position]

        holder.tvKeyWord.text = StringUtils.splitString(item.data)
        holder.tvKeyWord.background = DrawableUtils.generateDrawable(item.color)
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val tvKeyWord: TextView = mView.tvKeyWord
    }
}