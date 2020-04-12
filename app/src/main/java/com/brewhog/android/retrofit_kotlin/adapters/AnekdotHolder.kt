package com.brewhog.android.retrofit_kotlin.adapters

import android.os.Build
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.brewhog.android.retrofit_kotlin.pojo.Anekdot
import kotlinx.android.synthetic.main.anekdot_item.view.*

class AnekdotHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(anekdot: Anekdot){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            itemView.post_text.text = Html.fromHtml(anekdot.elementPureHtml,Html.FROM_HTML_MODE_LEGACY)
        }else{
            itemView.post_text.text = Html.fromHtml(anekdot.elementPureHtml)
        }
        itemView.site_text.text = anekdot.site
    }
}