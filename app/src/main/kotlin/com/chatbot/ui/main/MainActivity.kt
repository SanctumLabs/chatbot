package com.chatbot.ui.main

import ai.api.AIListener
import ai.api.android.AIService
import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.chatbot.R
import com.chatbot.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), AIListener, MainView, View.OnClickListener {

    @Inject
    lateinit var mainAdapter: MainAdapter
    var flagFab: Boolean = true
    private val audioRequestPermissionCode = 1

    @Inject
    lateinit var aiService: AIService

    @Inject
    lateinit var mainPresenter: MainPresenter<MainView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent.injectActivity(this)

        mainPresenter.onAttach(this)

        mainPresenter.onViewCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.onResume()
    }

    override fun onStop() {
        mainPresenter.onDetach()
        super.onStop()
    }

    override fun onDestroy() {
        mainPresenter.onDetach()
        super.onDestroy()
    }

    override fun setupListeners() {
        aiService.setListener(this)
        addBtn.setOnClickListener(this)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val sendImg = BitmapFactory.decodeResource(resources,
                        R.drawable.ic_send_white_24dp)
                val micImage = BitmapFactory.decodeResource(resources,
                        R.drawable.ic_mic_white_24dp)

                if (s.toString().trim { it <= ' ' }.isNotEmpty() && flagFab) {
                    imageViewAnimatedChange(fabImgView, sendImg)
                    flagFab = false
                } else if (s.toString().trim { it <= ' ' }.isEmpty()) {
                    imageViewAnimatedChange(fabImgView, micImage)
                    flagFab = true
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    override fun requestAudioPermission() {
        if (!hasPermission(Manifest.permission.RECORD_AUDIO)) {
            requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), audioRequestPermissionCode)
        }
    }

    override fun setupAdapterAndRecycler() {
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        mainAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)

                val msgCount = mainAdapter.itemCount
                val lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()

                if (lastVisiblePosition == -1 || positionStart >= msgCount - 1 && lastVisiblePosition == positionStart - 1) {
                    recyclerView.scrollToPosition(positionStart)
                }
            }
        })

        recyclerView.adapter = mainAdapter
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.addBtn -> {
                val message = editText.text.toString().trim { it <= ' ' }
                if (!message.isEmpty()) {
                    mainPresenter.onSendMessageClicked(message)
                    editText.setText("")
                }
            }
        }
    }

    /**
     * Animation change handler for the send button
     * */
    private fun imageViewAnimatedChange(v: ImageView, new_image: Bitmap) {
        val animOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
        val animIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        animOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                v.setImageBitmap(new_image)
                animIn.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationRepeat(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {}
                })
                v.startAnimation(animIn)
            }
        })
        v.startAnimation(animOut)
    }

    /**
     * What should we do with the permissions we now have?
     * */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == audioRequestPermissionCode) {

        }
    }

    override fun onResult(response: ai.api.model.AIResponse) {
        mainPresenter.onAiResult(response)
    }

    override fun onError(error: ai.api.model.AIError) {
        mainPresenter.onAiError(error)
    }

    override fun onAudioLevel(level: Float) {

    }

    override fun onListeningStarted() {

    }

    override fun onListeningCanceled() {

    }

    override fun onListeningFinished() {

    }
}
