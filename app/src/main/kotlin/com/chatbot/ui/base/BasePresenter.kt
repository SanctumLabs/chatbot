package com.chatbot.ui.base

/**
 * @author lusinabrian on 01/04/17
 * * Every presenter in the app must either extend this interface or implement it indicating the view
 * * type that must be attached
 */

interface BasePresenter<in V : BaseView> {
    fun onAttach(mBaseView: V)

    fun onDetach()
}
