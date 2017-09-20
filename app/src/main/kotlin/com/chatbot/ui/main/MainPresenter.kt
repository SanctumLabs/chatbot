package com.chatbot.ui.main

import android.os.Bundle

/**
 * @author lusinabrian on 20/09/17.
 * @Notes Presenter layer
 */
interface MainPresenter<V : MainView> {

    fun onAttach(mBaseView: V)

    /**
     * on Start
     * */
    fun onStart()

    /**
     * ON Resume
     * */
    fun onResume()

    fun onDetach()

    fun onViewCreated(savedInstanceState : Bundle?)

    /**
     * Callback for when the send message button is clicked
     * */
    fun onSendMessageClicked(message : String)

    /**
     * callback for when the audion button is clicked
     * */
    fun onAudioButtonClicked()
}