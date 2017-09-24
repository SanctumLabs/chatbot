package com.chatbot.ui.main

import ai.api.model.AIError
import ai.api.model.AIResponse
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
        dataManager.startAiService()
    }

    override fun onResume() {
        dataManager.startAiService()
        baseView.setupListeners()
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        baseView.setupAdapterAndRecycler()
    }

    override fun onSendMessageClicked(message: String) {
        dataManager.postUserMessage(message, "user")
        dataManager.postMessageQueryToAI(message)
        compositeDisposable.addAll(
                dataManager.makeAIRequest()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({
                            // on next, post bot message to db ref
                            val result = it.result.fulfillment.speech
                            dataManager.postBotMessage(result)
                        }, {
                            // on error
                            error("Error getting AI response ${it.message} => $it")
                        }, {
                            // on complete
                        })
        )
    }

    /**
     * Post ai results to db ref
     * */
    override fun onAiResult(response: AIResponse) {
        val result = response.result
        val message = result.resolvedQuery

        dataManager.postUserMessage(message)

        val reply = result.fulfillment.speech
        dataManager.postBotMessage(reply)
    }

    override fun onAiError(error: AIError) {

    }

    override fun onAudioButtonClicked() {

    }

    override fun onDetach() {
        super.onDetach()
        dataManager.stopAiService()
        compositeDisposable.dispose()
    }
}