package com.example.memeapplication.ui.gallery

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.memeapplication.ui.work_with_api.Entrie

class EntrieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class EntrieItemCallback<entrie : Entrie> : DiffUtil.ItemCallback<Entrie>() {
    override fun areItemsTheSame(oldItem: Entrie, newItem: Entrie) = oldItem.toString() == newItem.toString()

    override fun areContentsTheSame(oldItem: Entrie, newItem: Entrie) = oldItem == newItem
}