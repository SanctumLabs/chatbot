package com.chatbot.di.components

import android.app.Application
import android.content.Context
import com.chatbot.app.ChatBotApp
import com.chatbot.data.DataManager
import com.chatbot.di.modules.AppModule
import com.chatbot.di.modules.DatabaseModule
import com.chatbot.di.qualifier.AppCtxQualifier
import dagger.Component
import javax.inject.Singleton

/**
 * @author lusinabrian on 20/09/17.
 * @Notes app component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, DatabaseModule::class))
interface AppComponent {
    fun injectApp(chatBotApp: ChatBotApp)

    @AppCtxQualifier
    fun context(): Context

    fun application(): Application

    fun getDataManager() : DataManager
}