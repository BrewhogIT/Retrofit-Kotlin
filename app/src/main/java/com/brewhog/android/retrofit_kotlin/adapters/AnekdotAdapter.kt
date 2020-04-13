package com.brewhog.android.retrofit_kotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brewhog.android.retrofit_kotlin.R
import com.brewhog.android.retrofit_kotlin.pojo.Anekdot

class AnekdotAdapter(var anekdots : ArrayList<Anekdot>) : RecyclerView.Adapter<AnekdotHolder>() {

    fun setAnekdotList(newList: ArrayList<Anekdot>){
        val diffUtillCallback = AnekdotDiffUtilCallback(this.anekdots,newList)
        val diffResult = DiffUtil.calculateDiff(diffUtillCallback)

        this.anekdots = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnekdotHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anekdot_item,parent,false)
        return AnekdotHolder(view)
    }

    override fun getItemCount(): Int {
        return anekdots.size
    }

    override fun onBindViewHolder(holder: AnekdotHolder, position: Int) {
        holder.bind(anekdots[position])
    }
}