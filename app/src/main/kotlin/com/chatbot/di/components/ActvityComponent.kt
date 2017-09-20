package com.chatbot.di.components

import com.chatbot.di.modules.ActivityModule
import dagger.Component

/**
 * @author lusinabrian on 20/09/17.
 * @Notes
 */
@Component(modules = arrayOf(ActivityModule::class))
interface ActvityComponent {
}