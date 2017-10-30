package com.chatbot.ui.auth.login

import android.os.Bundle
import com.chatbot.R
import com.chatbot.ui.base.BaseActivity
import javax.inject.Inject

/**
 * @author lusinabrian on 30/10/17.
 * @Notes Login Activity to allow users to login to the application
 */
class LoginActivity : BaseActivity(), LoginView {

    @Inject
    lateinit var loginPresenter: LoginPresenter<LoginView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // inject dependencies into this activity
        activityComponent.injectLoginActivity(this)

        // attach your presenter
        loginPresenter.onAttach(this)
    }

}