package com.example.memeapplication.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

import com.example.memeapplication.ui.work_with_api.Entrie
import com.example.memeapplication.R

class EntrieAdapter(
    inline val bind: (item: Entrie, holder: EntrieViewHolder, itemCount: Int) -> Unit
) : ListAdapter<Entrie, EntrieViewHolder>(EntrieItemCallback<Entrie>()) {

    override fun onBindViewHolder(holder: EntrieViewHolder, position: Int) {
        bind(getItem(position), holder, itemCount)
    }

    fun addListElements(list: List<Entrie>){
        currentList.addAll(list)
    }

    override fun getItemViewType(position: Int) = R.layout.api_element

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntrieViewHolder {
        return EntrieViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun getItemCount() = currentList.size
}

