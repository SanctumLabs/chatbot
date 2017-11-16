package com.chatbot.ui.splash

import com.chatbot.data.DataManager
import com.chatbot.data.io.SchedulerProvider
import com.chatbot.ui.base.BasePresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author lusinabrian
 * @notes: Presenter layer to interact with data and view
 */
class SplashPresenterImpl<V : SplashView>
@Inject
constructor(mDatamanager: DataManager, mCompositeDisposable: CompositeDisposable,
            mSchedulerProvider: SchedulerProvider) : BasePresenterImpl<V>(mDatamanager, mSchedulerProvider, mCompositeDisposable), SplashPresenter<V> {

    override fun onAttach(mBaseView: V) {
        super.onAttach(mBaseView)
        baseView.setupView()
    }

    override fun onStart() {
        baseView.openRegisterScreen()
    }

    override fun onUserSessionActive() {
        baseView.openMainScreen()
    }
}
