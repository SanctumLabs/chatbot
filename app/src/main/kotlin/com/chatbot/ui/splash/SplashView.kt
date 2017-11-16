package com.chatbot.ui.splash

import com.chatbot.ui.base.BaseView

interface SplashView : BaseView {

    fun setupView()

    /**
     * Opens register screen
     * */
    fun openRegisterScreen()

    /**
     * Opens Main screen
     * */
    fun openMainScreen()
}