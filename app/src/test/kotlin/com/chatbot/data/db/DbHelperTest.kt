package com.chatbot.data.db

import com.chatbot.ui.ChatMessage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


/**
 * @author lusinabrian on 24/09/17.
 * @Notes DB Helper impl tests
 */
@Ignore("Testing FirebaseDatabase reference not creating required classes to test")
@RunWith(MockitoJUnitRunner::class)
class DbHelperTest {
    @Mock lateinit var mMockDatabaseRef: DatabaseReference
    lateinit var userMessage: String
    lateinit var botMessage: String
    lateinit var dbHelper: DbHelperImpl
    lateinit var chatMessage: ChatMessage

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

        val mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase::class.java)
        `when`(mockedFirebaseDatabase.reference).thenReturn(mMockDatabaseRef)

        userMessage = "Hello ChatBot"
        botMessage = "Hello User"
        chatMessage = ChatMessage()
        dbHelper = DbHelperImpl(mMockDatabaseRef)
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }

    @Test
    fun testPostUserMessageCallsDbRef() {
        dbHelper.postUserMessage(botMessage)
        chatMessage.msgText = userMessage
        verify(mMockDatabaseRef, times(1)).push().setValue(chatMessage)
    }

    @Test
    fun testPostBotMessageSetsBotMsgValue() {
        dbHelper.postBotMessage(botMessage)
        chatMessage.msgText = botMessage
        verify(mMockDatabaseRef, times(1)).push().setValue(chatMessage)
    }

}