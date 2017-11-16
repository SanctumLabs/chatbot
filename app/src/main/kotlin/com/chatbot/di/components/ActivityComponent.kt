package com.chatbot.di.components

import com.chatbot.di.modules.ActivityModule
import com.chatbot.di.scopes.ActivityScope
import com.chatbot.ui.auth.login.LoginActivity
import com.chatbot.ui.auth.recovery.RecoverPassActivity
import com.chatbot.ui.auth.register.RegisterActivity
import com.chatbot.ui.main.MainActivity
import com.chatbot.ui.splash.SplashActivity
import dagger.Component

/**
 * @author lusinabrian on 20/09/17.
 * @Notes
 */
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {

    /**
     * Inject dependencies into Splash Activity
     * @param splashActivity Entry point of application
     * */
    fun injectSplashActivity(splashActivity: SplashActivity)

    fun injectMainActivity(mainActivity: MainActivity)

    /**
     * Inject dependencies into Login Activity
     * @param loginActivity Login Activity Instance
     * */
    fun injectLoginActivity(loginActivity: LoginActivity)

    /**
     * Inject dependencies into Register Activity
     * @param registerActivity Instance of Register Activity
     * */
    fun injectRegisterActivity(registerActivity: RegisterActivity)

    /**
     * Inject dependencies into Recovery Activity
     * @param recoverPassActivity
     * */
    fun injectRecoverPassActivity(recoverPassActivity: RecoverPassActivity)
}