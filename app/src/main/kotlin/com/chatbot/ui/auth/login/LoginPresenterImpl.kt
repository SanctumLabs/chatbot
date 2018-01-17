package com.chatbot.ui.auth.login

import android.support.design.widget.Snackbar
import com.chatbot.R
import com.chatbot.data.DataManager
import com.chatbot.data.io.SchedulerProvider
import com.chatbot.ui.base.BasePresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author lusinabrian
 * @notes: Presenter layer to interact with data and view
 */
class LoginPresenterImpl<V : LoginView>
@Inject
constructor(mDatamanager: DataManager, mCompositeDisposable: CompositeDisposable,
            mSchedulerProvider: SchedulerProvider) : BasePresenterImpl<V>(mDatamanager, mSchedulerProvider, mCompositeDisposable), LoginPresenter<V> {

    override fun onAttach(mBaseView: V) {
        super.onAttach(mBaseView)
        baseView.setListeners()
    }

    override fun onLoginButtonClicked(usernameOrEmail: String, password: String) {
        // checks with firebase whether these credentials exist
        //dataManager
    }

    override fun onPasswordError() {
        super.onPasswordError()
        baseView.setPasswordError(R.string.error_password)
    }

    override fun onUsernameOrEmailError() {
        super.onUsernameOrEmailError()
        baseView.setUsernameError(R.string.error_username)
    }

    override fun onNetworkDisconnected() {
        super.onNetworkDisconnected()
        baseView.displaySnackbar(R.string.error_network_offline, Snackbar.LENGTH_LONG,
                R.layout.activity_login, true)
    }
}
