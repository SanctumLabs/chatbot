package com.chatbot.ui.auth.recovery

import com.chatbot.ui.base.BaseActivity
import android.os.Bundle
import com.chatbot.R
import javax.inject.Inject

/**
 * @author lusinabrian on 30/10/17.
 * @Notes Recover password activity
 */
class RecoverPassActivity : BaseActivity(), RecoverPassView {

    @Inject
    lateinit var recoverPassPresenter: RecoverPassPresenter<RecoverPassView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_pass)

        // inject dependencies into this activity
        activityComponent.injectRecoverPassActivity(this)

        // attach your presenter
        recoverPassPresenter.onAttach(this)
    }
}