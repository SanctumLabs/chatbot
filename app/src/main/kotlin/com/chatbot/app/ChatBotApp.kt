package com.chatbot.app

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.chatbot.di.components.AppComponent
import com.chatbot.di.components.DaggerAppComponent
import com.chatbot.di.modules.AIModule
import com.chatbot.di.modules.AppModule
import com.chatbot.di.modules.DatabaseModule
import com.chatbot.di.modules.FirebaseModule
import com.crashlytics.android.Crashlytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.fabric.sdk.android.Fabric
import javax.inject.Inject


class ChatBotApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .databaseModule(DatabaseModule())
                .aIModule(AIModule())
                .firebaseModule(FirebaseModule())
                .build()
    }

    @Inject
    lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate() {
        super.onCreate()

        appComponent.injectApp(this)

        Fabric.with(this, Crashlytics())

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        logUser()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    /**
     * Logs the user to Crashlytics if the exist
     * */
    private fun logUser() {
        val firebaseUser = firebaseAuth.currentUser

        if(firebaseUser != null){
            Crashlytics.setUserIdentifier(firebaseUser.uid)
            Crashlytics.setUserEmail(firebaseUser.email)
            Crashlytics.setUserName(firebaseUser.displayName)
        }
    }

}
