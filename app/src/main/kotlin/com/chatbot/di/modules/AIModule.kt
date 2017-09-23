package com.chatbot.di.modules

import ai.api.AIDataService
import ai.api.android.AIService
import ai.api.model.AIRequest
import android.content.Context
import com.chatbot.BuildConfig
import com.chatbot.data.ai.AIHelperImpl
import com.chatbot.data.ai.AiHelper
import com.chatbot.di.qualifier.AppCtxQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author lusinabrian on 23/09/17.
 * @Notes AI module provides dependencies for API.AI
 */
@Module
class AIModule {

    @Provides
    fun provideAiHelper(aiHelper: AIHelperImpl): AiHelper {
        return aiHelper
    }

    @Provides
    @Named("AiRequest")
    fun provideAiRequest(): AIRequest {
        return AIRequest()
    }

    @Provides
    fun provideAiService(@Named("AiConfig") aiConfig: ai.api.android.AIConfiguration, @AppCtxQualifier context: Context): AIService {
        return AIService.getService(context, aiConfig)
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
}