package com.chatbot.di.modules

import ai.api.AIDataService
import ai.api.android.AIService
import ai.api.model.AIRequest
import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.chatbot.BuildConfig
import com.chatbot.di.qualifier.ActivityCtxQualifier
import com.chatbot.di.scopes.ActivityScope
import com.chatbot.ui.main.MainPresenter
import com.chatbot.ui.main.MainPresenterImpl
import com.chatbot.ui.main.MainView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author lusinabrian on 20/09/17.
 * @Notes module for activities
 */
@Module
class ActivityModule(val mActivity: AppCompatActivity) {

    @Provides
    @ActivityCtxQualifier
    fun provideContext(): Context {
        return mActivity
    }

    @Provides
    fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    @ActivityScope
    fun provideMainPresenter(mainPresenter: MainPresenterImpl<MainView>): MainPresenter<MainView> {
        return mainPresenter
    }

    @Provides
    fun provideAiService(@Named("AiConfig") aiConfig: ai.api.android.AIConfiguration): AIService {
        return AIService.getService(mActivity, aiConfig)
    }

    @Provides
    fun provideAiDataService(@Named("AiConfig") aiConfig: ai.api.android.AIConfiguration): AIDataService {
        return AIDataService(aiConfig)
    }

    @Provides
    @Named("AiConfig")
    fun provideAiConfiguration(): ai.api.android.AIConfiguration {
        return ai.api.android.AIConfiguration(BuildConfig.API_AI_CLIENT_ACCESS_TOKEN,
                ai.api.AIConfiguration.SupportedLanguages.English,
                ai.api.android.AIConfiguration.RecognitionEngine.System)
    }

//    @Provides
//    @Named("AiRequest")
//    fun provideAiRequest(): AIRequest {
//        return AIRequest()
//    }

}