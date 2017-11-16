package com.chatbot.ui.splash

import com.chatbot.ui.base.BasePresenter

interface SplashPresenter<V : SplashView> : BasePresenter<V> {

    /**
     * Triggered on start lifecycle method
     * */
    fun onStart()

    /**
     * If the user session is active, the start up the main activity
     * */
    fun onUserSessionActive()
}