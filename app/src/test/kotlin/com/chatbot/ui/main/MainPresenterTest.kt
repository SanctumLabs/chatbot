package com.chatbot.ui.main

import ai.api.model.AIResponse
import android.os.Bundle
import com.chatbot.data.DataManager
import com.chatbot.data.TestSchedulerProvider
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author lusinabrian on 24/09/17.
 * @Notes Tests for MainPresenter
 */

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {
    @Mock lateinit var mMockDataManager: DataManager
    @Mock lateinit var mMockMainView: MainView
    @Mock lateinit var mMockSavedInstanceState: Bundle
    @Mock lateinit var mMockAiResponse: AIResponse
    lateinit var userMessage: String
    lateinit var mainPresenter: MainPresenter<MainView>
    lateinit var mTestScheduler: TestScheduler

    companion object {

        @JvmStatic
        @BeforeClass
        @Throws(Exception::class)
        fun onlyOnce() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler({ Schedulers.trampoline() })
        }
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val compositeDisposable = CompositeDisposable()
        userMessage = "Hello ChatBot"
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)

        mainPresenter = MainPresenterImpl(mMockDataManager, testSchedulerProvider,
                compositeDisposable)
        mainPresenter.onAttach(mMockMainView)

        // we check if requesting for audio permissions passed the test
        verify(mMockMainView, times(1)).requestAudioPermission()
    }

    @After
    fun tearDown() {
        mainPresenter.onDetach()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testOnStartStartsAiService() {
        mainPresenter.onStart()
        verify(mMockDataManager, times(1)).startAiService()
    }

    @Test
    fun testOnResumeStartsAiServiceSetsUpListeners() {
        mainPresenter.onResume()
        verify(mMockDataManager, times(1)).startAiService()
        verify(mMockMainView, times(1)).setupListeners()
    }

    @Test
    fun testOnViewCreatedSetsUpRecyclerView() {
        mainPresenter.onViewCreated(mMockSavedInstanceState)

        verify(mMockMainView, times(1)).setupAdapterAndRecycler()
    }

    @Test
    fun testOnSendMessageClickedSendsMessage() {
        `when`(mMockDataManager.makeAIRequest()).thenReturn(Observable.just(mMockAiResponse))

        mainPresenter.onSendMessageClicked(userMessage)

        verify(mMockDataManager, times(1)).postUserMessage(userMessage)
        verify(mMockDataManager, times(1)).postMessageQueryToAI(userMessage)

//        verify(mMockDataManager, times(1)).postBotMessage(ArgumentMatchers.anyString())
    }

    @Ignore("AiResponse result returns NPE")
    @Test
    fun testOnAiResultPostsUserAndBotMessages() {
        `when`(mMockAiResponse.result.resolvedQuery).thenReturn("")
        mainPresenter.onAiResult(mMockAiResponse)
        val result = mMockAiResponse.result
        val message = result.resolvedQuery

        verify(mMockDataManager, times(1)).postUserMessage(message)

        val reply = result.fulfillment.speech
        verify(mMockDataManager, times(1)).postBotMessage(reply)
    }

    @Test
    fun testOnDetachStopsAiService() {
        mainPresenter.onDetach()

        verify(mMockDataManager, times(1)).stopAiService()
    }

}