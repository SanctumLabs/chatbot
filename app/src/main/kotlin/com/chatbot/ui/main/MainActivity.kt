package com.chatbot.ui.main

import ai.api.AIListener
import ai.api.android.AIService
import ai.api.model.AIRequest
import android.Manifest
import android.content.Context
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

    lateinit var aiRequest: AIRequest

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

    override fun setupAiServiceAndRequest() {
        aiService.setListener(this)
        aiRequest = AIRequest()
    }

    override fun setupListeners() {
        addBtn.setOnClickListener(this)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val fab_img = findViewById<View>(R.id.fab_img) as ImageView
                val img = BitmapFactory.decodeResource(resources, R.drawable.ic_send_white_24dp)
                val img1 = BitmapFactory.decodeResource(resources, R.drawable.ic_mic_white_24dp)


                if (s.toString().trim { it <= ' ' }.isNotEmpty() && flagFab) {
                    ImageViewAnimatedChange(this@MainActivity, fab_img, img)
                    flagFab = false

                } else if (s.toString().trim { it <= ' ' }.isEmpty()) {
                    ImageViewAnimatedChange(this@MainActivity, fab_img, img1)
                    flagFab = true

                }


            }

            override fun afterTextChanged(s: Editable) {

            }
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

//        mainAdapter = object : FirebaseRecyclerAdapter<ChatMessage, ChatViewHolder>(ChatMessage::class.java, R.layout.item_chat, ChatViewHolder::class.java, databaseReference.child("chat")) {
//            override fun populateViewHolder(viewHolder: ChatViewHolder, model: ChatMessage, position: Int) {
//
//                if (model.msgUser == "user") {
//
//                    viewHolder.rightText.text = model.msgText
//
//                    viewHolder.rightText.visibility = View.VISIBLE
//                    viewHolder.leftText.visibility = View.GONE
//                } else {
//                    viewHolder.leftText.text = model.msgText
//
//                    viewHolder.rightText.visibility = View.GONE
//                    viewHolder.leftText.visibility = View.VISIBLE
//                }
//            }
//        }

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
                if (message != "") {
                    mainPresenter.onSendMessageClicked(message)
                } else {
                    aiService.startListening()
                }
                editText.setText("")

                // val chatMessage = ChatMessage(message, "user")
                // databaseReference.child("chat").push().setValue(chatMessage)
                // aiRequest.setQuery(message)
//                object : AsyncTask<AIRequest, Void, AIResponse>() {
//                    override fun doInBackground(vararg aiRequests: AIRequest): AIResponse? {
//                        val request = aiRequests[0]
//                        try {
//                            return aiDataService.request(aiRequest)
//                        } catch (e: AIServiceException) {
//                        }
//                        return null
//                    }
//
//                    override fun onPostExecute(response: AIResponse?) {
//                        if (response != null) {
//                            val result = response.result
//                            val reply = result.fulfillment.speech
//                            val chatMessage = ChatMessage(reply, "bot")
//                            databaseReference.child("chat").push().setValue(chatMessage)
//                        }
//                    }
//                }.execute(aiRequest)
            }
        }
    }

    fun ImageViewAnimatedChange(c: Context, v: ImageView, new_image: Bitmap) {
        val anim_out = AnimationUtils.loadAnimation(c, R.anim.zoom_out)
        val anim_in = AnimationUtils.loadAnimation(c, R.anim.zoom_in)
        anim_out.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                v.setImageBitmap(new_image)
                anim_in.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationRepeat(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {}
                })
                v.startAnimation(anim_in)
            }
        })
        v.startAnimation(anim_out)
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
