package com.chatbot.ui.auth.login

import com.chatbot.ui.base.BasePresenter

interface LoginPresenter<V : LoginView> : BasePresenter<V> {

    /**
     * Action to handle login press. On Login Button clicked. we pass the username and the password
     * to the server to check whether the username and the password are valid and return a proper
     * response back to the view
     * @param usernameOrEmail User's username or email used on registration
     * @param password user password used on registration
     * */
    fun onLoginButtonClicked(usernameOrEmail : String , password: String)

}