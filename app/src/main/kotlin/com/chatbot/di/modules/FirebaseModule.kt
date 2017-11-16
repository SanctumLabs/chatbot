package com.chatbot.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.google.firebase.auth.FirebaseAuth

/**
 * @author lusinabrian on 08/11/17.
 * @Notes Firebase module to provide dependencies for Firebase
 */
@Module
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}