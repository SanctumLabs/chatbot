package com.chatbot.ui


import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.chatbot.R

class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var leftText = itemView.findViewById<TextView>(R.id.leftText)
    var rightText = itemView.findViewById<TextView>(R.id.rightText)
}
