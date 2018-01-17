package com.chatbot.di.modules

import com.chatbot.data.api.ApiHelper
import com.chatbot.data.api.ApiHelperImpl
import dagger.Module
import dagger.Provides

/**
 * @author lusinabrian on 17/01/18.
 * @Notes
 */
@Module
class ApiModule {

    @Provides
    fun provideApiHelper(apiHelper: ApiHelperImpl) : ApiHelper{
        return apiHelper
    }
}