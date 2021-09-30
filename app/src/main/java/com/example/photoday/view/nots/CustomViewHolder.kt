package com.example.photoday.view.nots

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: Pair<Data, Boolean>)
}