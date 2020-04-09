package com.tpa.HelepDoc.adapters.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tpa.HelepDoc.R

class ChatLeftViewHolder (view:View):RecyclerView.ViewHolder(view){
    var tvName:TextView = view.findViewById(R.id.tv_name)
    var tvMessage:TextView = view.findViewById(R.id.tv_message)


    fun bindItem(name:String?, message:String?){
        tvName.text = name
        tvMessage.text = message
    }

}