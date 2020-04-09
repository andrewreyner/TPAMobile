package com.tpa.HelepDoc.adapters.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.models.Chat
import org.w3c.dom.Text

class LatestChatViewHolder(view:View):RecyclerView.ViewHolder(view){
    var tvName:TextView = view.findViewById(R.id.tv_name)
    var tvMessage:TextView = view.findViewById(R.id.tv_message)
    var tvDate:TextView =view.findViewById(R.id.tv_date)
    var ivImage:ImageView = view.findViewById(R.id.iv_image)



}