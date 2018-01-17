package com.chatbot.ui.base

/**
 * @author lusinabrian on 01/04/17
 * * Every presenter in the app must either extend this interface or implement it indicating the view
 * * type that must be attached
 */

interface BasePresenter<in V : BaseView> {
    fun onAttach(mBaseView: V)

    fun onDetach()

    /**
     * On Network disconnected action.
     * This can be used to display a snackbar for informational purposes
     * */
    fun onNetworkDisconnected()

    /**
     * On username or email error encountered when filling in details of a form
     * */
    fun onUsernameOrEmailError()

    /**
     * used to display a password error on password field in form
     * */
    fun onPasswordError()
}
