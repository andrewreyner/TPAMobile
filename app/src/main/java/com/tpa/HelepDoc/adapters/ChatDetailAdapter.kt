package com.tpa.HelepDoc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.adapters.viewHolders.ChatLeftViewHolder
import com.tpa.HelepDoc.adapters.viewHolders.ChatRightViewHolder
import com.tpa.HelepDoc.models.Message

class ChatDetailAdapter(var messages: ArrayList<Message>,val userId:String, val opponentName:String): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val OWNTYPE= 1
    private val OTHERTYPE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == OTHERTYPE){
            val view:View = LayoutInflater.from(parent.context).inflate(R.layout.chat_item_left, parent, false)
            ChatLeftViewHolder(view)
        }else{
            val view:View = LayoutInflater.from(parent.context).inflate(R.layout.chat_item_right, parent, false)
            ChatRightViewHolder(view)

        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == OTHERTYPE){
            var h:ChatLeftViewHolder = holder as ChatLeftViewHolder
            h.bindItem(opponentName,messages[position].message)
        }else{
            var h:ChatRightViewHolder = holder as ChatRightViewHolder
            h.bindItem(messages[position].message)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(messages[position].senderId.equals(userId)){
            OWNTYPE
        }else{
            OTHERTYPE
        }
    }
}



