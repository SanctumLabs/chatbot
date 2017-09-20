package com.chatbot.di.modules

import android.app.Application
import android.content.Context
import com.chatbot.app.ChatBotApp
import com.chatbot.di.qualifier.AppCtxQualifier
import dagger.Module
import dagger.Provides

/**
 * @author lusinabrian on 20/09/17.
 * @Notes app module
 */
@Module
class AppModule(private val mApplication: Application) {

    @Provides
    @AppCtxQualifier
    fun provideContext(): Context {
        return mApplication
    }

    @Provides
    fun provideApplication(): Application {
        return mApplication
    }

}