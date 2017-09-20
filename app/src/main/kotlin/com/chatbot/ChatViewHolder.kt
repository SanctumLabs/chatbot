package com.chatbot


import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var leftText: TextView = itemView.findViewById(R.id.leftText) as TextView
    var rightText: TextView = itemView.findViewById(R.id.rightText) as TextView

}
