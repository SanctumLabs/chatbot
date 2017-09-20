package com.chatbot


import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var leftText = itemView.findViewById<TextView>(R.id.leftText)
    var rightText = itemView.findViewById<TextView>(R.id.rightText)
}
