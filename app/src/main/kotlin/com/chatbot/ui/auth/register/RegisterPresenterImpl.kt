package com.chatbot.ui.auth.register

import android.os.Build
import com.chatbot.data.DataManager
import com.chatbot.data.io.SchedulerProvider
import com.chatbot.ui.base.BasePresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author lusinabrian
 * @notes: Presenter layer to interact with data and view
 */
class RegisterPresenterImpl<V : RegisterView>
@Inject
constructor(mDatamanager: DataManager, mCompositeDisposable: CompositeDisposable,
            mSchedulerProvider: SchedulerProvider
) : BasePresenterImpl<V>(mDatamanager, mSchedulerProvider, mCompositeDisposable), RegisterPresenter<V> {

    override fun onAttach(mBaseView: V) {
        super.onAttach(mBaseView)
        baseView.setListeners()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            baseView.showEnterAnimation()
        }
    }

    override fun onRegisterClose() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            baseView.animateRevealClose()
        }
        closeRegisterScreen()
    }

    override fun closeRegisterScreen() {

    }

    override fun onTransitionEnd() {
        baseView.animateRevealShow()
    }
}
