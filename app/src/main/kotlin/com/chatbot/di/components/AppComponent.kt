package com.chatbot.di.components

import android.app.Application
import android.content.Context
import com.chatbot.app.ChatBotApp
import com.chatbot.di.modules.AppModule
import com.chatbot.di.qualifier.AppCtxQualifier
import dagger.Component
import javax.inject.Singleton

/**
 * @author lusinabrian on 20/09/17.
 * @Notes app component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun injectApp(chatBotApp: ChatBotApp)

    @AppCtxQualifier
    fun context(): Context

    fun application(): Application
}