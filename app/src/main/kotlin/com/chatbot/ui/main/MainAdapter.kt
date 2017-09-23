package com.chatbot.ui.main

import android.view.View
import com.chatbot.R
import com.chatbot.di.qualifier.DatabaseRefQualifier
import com.chatbot.ui.ChatMessage
import com.chatbot.ui.ChatViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

/**
 * @author lusinabrian on 23/09/17.
 * @Notes Main mainAdapter to handle the chats
 */
class MainAdapter
@Inject
constructor(@DatabaseRefQualifier databaseReference: DatabaseReference) : FirebaseRecyclerAdapter<ChatMessage, ChatViewHolder>(ChatMessage::class.java, R.layout.item_chat, ChatViewHolder::class.java,
        databaseReference.child("chat")) {

    override fun populateViewHolder(viewHolder: ChatViewHolder, model: ChatMessage,
                                    position: Int) {
        if (model.msgUser == "user") {
            viewHolder.rightText.text = model.msgText
            viewHolder.rightText.visibility = View.VISIBLE
            viewHolder.leftText.visibility = View.GONE
        } else {
            viewHolder.leftText.text = model.msgText

            viewHolder.rightText.visibility = View.GONE
            viewHolder.leftText.visibility = View.VISIBLE
        }
    }
}