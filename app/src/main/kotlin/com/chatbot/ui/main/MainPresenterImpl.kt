package com.chatbot.ui.main

import android.os.Bundle
import com.chatbot.data.DataManager
import com.chatbot.data.io.SchedulerProvider
import com.chatbot.ui.base.BasePresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author lusinabrian on 20/09/17.
 * @Notes presenter implementation
 */
class MainPresenterImpl<V : MainView>
@Inject
constructor(mDataManager: DataManager,
            mSchedulerProvider: SchedulerProvider,
            mCompositeDisposable: CompositeDisposable)
    : MainPresenter<V>, BasePresenterImpl<V>(mDataManager, mSchedulerProvider,
        mCompositeDisposable) {

    override fun onAttach(mBaseView: V) {
        super.onAttach(mBaseView)
        baseView.requestAudioPermission()
    }

    override fun onStart() {
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
        super.onDetach()
        compositeDisposable.dispose()
    }
}