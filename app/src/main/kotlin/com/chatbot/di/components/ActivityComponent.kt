package com.chatbot.di.components

import com.chatbot.di.modules.ActivityModule
import com.chatbot.di.scopes.ActivityScope
import com.chatbot.ui.main.MainActivity
import dagger.Component

/**
 * @author lusinabrian on 20/09/17.
 * @Notes
 */
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    fun injectActivity(mainActivity: MainActivity)
}