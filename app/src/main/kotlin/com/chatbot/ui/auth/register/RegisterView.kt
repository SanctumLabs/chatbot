package com.chatbot.ui.auth.register

import com.chatbot.ui.base.BaseView

interface RegisterView : BaseView {

    /**
     * Shows an enter animation
     * */
    fun showEnterAnimation()

    /**
     * Set listeners
     * */
    fun setListeners()

    /**
     * Close animation in a reveal manner
     * */
    fun animateRevealClose()

    /**
     * Animate reveal show
     * */
    fun animateRevealShow()
}