package com.chatbot.ui.auth.register

import com.chatbot.ui.base.BasePresenter

interface RegisterPresenter<V : RegisterView> : BasePresenter<V> {

    fun onRegisterClose()

    fun onTransitionEnd()
}