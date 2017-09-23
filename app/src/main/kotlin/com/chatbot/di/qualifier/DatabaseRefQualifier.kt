package com.chatbot.di.qualifier

import javax.inject.Qualifier

/**
 * @author lusinabrian on 23/09/17.
 * @Notes Database ref to qualify db reference for Firebase
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DatabaseRefQualifier