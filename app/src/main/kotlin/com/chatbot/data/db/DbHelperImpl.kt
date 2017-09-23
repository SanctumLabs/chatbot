package com.chatbot.data.db

import com.chatbot.di.qualifier.DatabaseRefQualifier
import com.chatbot.ui.ChatMessage
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author lusinabrian on 23/09/17.
 * @Notes db implementation for the database
 */
@Singleton
class DbHelperImpl
@Inject
constructor(@DatabaseRefQualifier val databaseReference: DatabaseReference) : DbHelper {

    override fun postUserMessage(message: String, user: String) {
        val chatMessage = ChatMessage(message, "user")
        databaseReference.child("chat").push().setValue(chatMessage)
    }

    override fun postBotMessage(botMessage: String) {
        val chatMessage = ChatMessage(botMessage, "bot")
        databaseReference.child("chat").push().setValue(chatMessage)
    }
}