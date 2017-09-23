package com.chatbot.app

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.chatbot.di.components.AppComponent
import com.chatbot.di.components.DaggerAppComponent
import com.chatbot.di.modules.AppModule
import com.chatbot.di.modules.DatabaseModule
import com.google.firebase.database.FirebaseDatabase

class ChatBotApp : Application() {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .databaseModule(DatabaseModule())
                .build()

        appComponent.injectApp(this)

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
