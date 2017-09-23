package com.chatbot.ui.main

import com.chatbot.ui.base.BaseView

/**
 * @author lusinabrian on 20/09/17.
 * @Notes view for main activity
 */
interface MainView : BaseView{

    /**
     * callback for when the mainAdapter and recycler are setup
     * */
    fun setupAdapterAndRecycler()

    /**
     * request audio permission*/
    fun requestAudioPermission()

    /**
     * sets up listeners
     * */
    fun setupListeners()
}