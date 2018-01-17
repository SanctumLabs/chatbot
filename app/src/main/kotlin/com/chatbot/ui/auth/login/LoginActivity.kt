package com.chatbot.ui.auth.login

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.chatbot.R
import com.chatbot.ui.auth.register.RegisterActivity
import com.chatbot.ui.base.BaseActivity
import com.chatbot.utils.isNetworkConnected
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.partial_social_login.*
import javax.inject.Inject

/**
 * @author lusinabrian on 30/10/17.
 * @Notes Login Activity to allow users to login to the application
 */
class LoginActivity : BaseActivity(), LoginView, View.OnClickListener {

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

    override fun onResume() {
        super.onResume()
        appComponent.networkSubject().subscribe {
            // if network is not available
            if (!it) {
                // display snackbar error message
                loginPresenter.onNetworkDisconnected()
            }
        }
    }

    override fun setListeners() {
        fab_login.setOnClickListener(this)
        button_login.setOnClickListener(this)
        fabTwitter.setOnClickListener(this)
        fabFacebook.setOnClickListener(this)
        fabGithub.setOnClickListener(this)
        fabGoogle.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_login -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val options = ActivityOptions.makeSceneTransitionAnimation(
                            this, fab_login, fab_login.transitionName)
                    startActivity(Intent(this, RegisterActivity::class.java),
                            options.toBundle())
                } else {
                    startActivity(Intent(this, RegisterActivity::class.java))
                }
            }

            R.id.button_login -> {
                // check if fields are valid before performing network call

                // is network available?
                if (isNetworkConnected(this)) {
                    // send user data to the server
                    val password = edittext_password.text.toString()
                    val usernameOrEmail = edittext_username.text.toString()

                    if (TextUtils.isEmpty(password)) {
                        // display error message on password field
                        loginPresenter.onPasswordError()
                    }
                    if (TextUtils.isEmpty(usernameOrEmail)) {
                        // display error message on username field
                        loginPresenter.onUsernameOrEmailError()
                    }

                    loginPresenter.onLoginButtonClicked(usernameOrEmail, password)
                } else {
                    // display error message
                    loginPresenter.onNetworkDisconnected()
                }
            }

            R.id.fabTwitter -> {

            }

            R.id.fabFacebook -> {

            }

            R.id.fabGithub -> {

            }

            R.id.fabGoogle -> {

            }
        }
    }

    override fun setPasswordError(errorMessage: String) {
        super.setPasswordError(errorMessage)
        edittext_password.error = errorMessage
    }

    override fun setUsernameError(errorMessage: String) {
        super.setUsernameError(errorMessage)
        edittext_username.error = errorMessage
    }
}