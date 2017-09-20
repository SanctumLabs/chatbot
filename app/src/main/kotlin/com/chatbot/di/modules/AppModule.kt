package com.chatbot.di.modules

import ai.api.AIDataService
import ai.api.android.AIService
import ai.api.model.AIRequest
import android.app.Application
import android.content.Context
import com.chatbot.BuildConfig
import com.chatbot.di.qualifier.AppCtxQualifier
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

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

//    @Provides
//    fun provideAiService(@Named("AiConfig") aiConfig: ai.api.android.AIConfiguration): AIService {
//        return AIService.getService(mApplication, aiConfig)
//    }
//
//    @Provides
//    @Singleton
//    fun provideAiDataService(@Named("AiConfig") aiConfig: ai.api.android.AIConfiguration): AIDataService {
//        return AIDataService(aiConfig)
//    }
//
//    @Provides
//    @Named("AiConfig")
//    fun provideAiConfiguration(): ai.api.android.AIConfiguration {
//        return ai.api.android.AIConfiguration(BuildConfig.API_AI_CLIENT_ACCESS_TOKEN,
//                ai.api.AIConfiguration.SupportedLanguages.English,
//                ai.api.android.AIConfiguration.RecognitionEngine.System)
//    }
//
//    @Provides
//    @Named("AiRequest")
//    fun provideAiRequest(): AIRequest {
//        return AIRequest()
//    }

}