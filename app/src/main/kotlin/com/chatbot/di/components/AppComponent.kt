package com.chatbot.di.components

import android.app.Application
import android.content.Context
import com.chatbot.app.ChatBotApp
import com.chatbot.data.DataManager
import com.chatbot.di.modules.*
import com.chatbot.di.qualifier.AppCtxQualifier
import dagger.Component
import io.reactivex.subjects.PublishSubject
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author lusinabrian on 20/09/17.
 * @Notes app component
 */
@Singleton
@Component(modules = [(AppModule::class), (DatabaseModule::class), (AIModule::class), (FirebaseModule::class), ApiModule::class])
interface AppComponent {
    fun injectApp(chatBotApp: ChatBotApp)

    @AppCtxQualifier
    fun context(): Context

    fun application(): Application

    fun getDataManager(): DataManager

    /**
     * Network Subject. This is posted based on network events
     * */
    @Named("NetworkSubject")
    fun networkSubject() : PublishSubject<Boolean>
}