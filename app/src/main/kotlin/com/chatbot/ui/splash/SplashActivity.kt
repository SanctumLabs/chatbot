package com.chatbot.ui.splash

import android.os.Bundle
import com.chatbot.R
import com.chatbot.anim.AnimType
import com.chatbot.anim.Animator
import com.chatbot.ui.auth.register.RegisterActivity
import com.chatbot.ui.base.BaseActivity
import com.chatbot.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * @author lusinabrian on 16/11/17.
 * @Notes Splash page,
 */
class SplashActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var splashPresenter: SplashPresenter<SplashView>

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // inject dependencies into this activity
        activityComponent.injectSplashActivity(this)

        // attach your presenter
        splashPresenter.onAttach(this)
    }

    override fun setupView() {
        val scaleY = Animator(AnimType.SCALEY, 5.0, 3.0, 0.5, 1.0)
        val rotate = Animator(AnimType.ROTATEY, 5.0, 3.0, 180.0, 0.0)
        rotate.delay = 100
        scaleY.delay = 200
        rotate.startSpring(splashImage)
        scaleY.startSpring(splashImage)
    }

    override fun onStart() {
        super.onStart()
        // if current user is null, start up the register activity
        if (firebaseAuth.currentUser == null) {
            splashPresenter.onStart()
        } else {
            splashPresenter.onUserSessionActive()
        }
    }

    override fun openRegisterScreen() {
        startActivity<RegisterActivity>()
    }

    override fun openMainScreen() {
        startActivity<MainActivity>()
    }
}