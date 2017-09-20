package com.chatbot.ui.main

import android.os.Bundle
import javax.inject.Inject

/**
 * @author lusinabrian on 20/09/17.
 * @Notes presenter implementation
 */
class MainPresenterImpl<V : MainView>
@Inject
constructor() : MainPresenter<V> {
    lateinit var baseView: V
        private set

    override fun onAttach(mBaseView: V) {
        this.baseView = mBaseView
    }

    override fun onStart() {
        baseView.requestAudioPermission()
        baseView.setupAiServiceAndRequest()
    }

    override fun onResume() {
        baseView.setupListeners()
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        baseView.setupAdapterAndRecycler()
    }

    override fun onSendMessageClicked(message: String) {
    }

    override fun onAudioButtonClicked() {
    }

    override fun onDetach() {
    }
}