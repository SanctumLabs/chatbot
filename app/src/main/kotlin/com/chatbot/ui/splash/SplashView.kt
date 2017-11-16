package com.chatbot.ui.splash

import com.chatbot.ui.base.BaseView

interface SplashView : BaseView {

    /**
     * Opens register screen
     * */
    fun openRegisterScreen()

    /**
     * Opens Main screen
     * */
    fun openMainScreen()
}