package com.chatbot.app

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class ChatBotApp : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}
