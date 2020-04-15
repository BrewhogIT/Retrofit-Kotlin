package com.brewhog.android.retrofit_kotlin.adapters

import androidx.recyclerview.widget.DiffUtil
import com.brewhog.android.retrofit_kotlin.pojo.Anekdot

class AnekdotDiffUtilCallback (var oldList : List<Anekdot>, var newList : List<Anekdot> ): DiffUtil.Callback(){

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
}