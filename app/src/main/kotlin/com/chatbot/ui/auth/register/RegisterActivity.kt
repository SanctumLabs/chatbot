package com.chatbot.ui.auth.register

import android.os.Bundle
import com.chatbot.R
import com.chatbot.ui.base.BaseActivity
import javax.inject.Inject

/**
 * @author lusinabrian on 30/10/17.
 * @Notes Register Activity to create a new user with the system
 */
class RegisterActivity : BaseActivity(), RegisterView {

    @Inject
    lateinit var registerPresenter: RegisterPresenter<RegisterView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // inject dependencies into this activity
        activityComponent.injectRegisterActivity(this)

        // attach your presenter
        registerPresenter.onAttach(this)
    }
}