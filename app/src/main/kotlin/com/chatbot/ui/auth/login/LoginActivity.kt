package com.chatbot.ui.auth.login

import android.os.Bundle
import android.view.View
import com.chatbot.R
import com.chatbot.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.partial_social_login.*
import javax.inject.Inject
import com.chatbot.ui.auth.register.RegisterActivity
import android.content.Intent
import com.chatbot.R.id.fab
import android.support.v4.view.ViewCompat.getTransitionName
import android.app.ActivityOptions
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.os.Build.VERSION
import android.os.Build.VERSION.SDK_INT



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

    override fun setListeners() {
        loginFab.setOnClickListener(this)
        fabTwitter.setOnClickListener(this)
        fabFacebook.setOnClickListener(this)
        fabGithub.setOnClickListener(this)
        fabGoogle.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            loginFab -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.exitTransition = null
                    window.enterTransition = null
                    val options = ActivityOptions.makeSceneTransitionAnimation(this,
                            loginFab, loginFab.transitionName)
                    startActivity(Intent(this, RegisterActivity::class.java),
                            options.toBundle())
                } else {
                    startActivity(Intent(this, RegisterActivity::class.java))
                }
            }

            loginButton -> {

            }

            fabTwitter -> {

            }

            fabFacebook -> {

            }

            fabGithub -> {

            }

            fabGoogle -> {

            }
        }
    }

}