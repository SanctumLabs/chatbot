package com.chatbot.data

import ai.api.model.AIResponse
import com.chatbot.data.ai.AiHelper
import com.chatbot.data.api.ApiHelper
import com.chatbot.data.db.DbHelper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author lusinabrian on 23/09/17.
 * @Notes Implementation for the datamanager
 */
@Singleton
class DataManagerImpl
@Inject
constructor(val dbHelper: DbHelper,
            val aiHelper: AiHelper,
            val apiHelper: ApiHelper) : DataManager {

    override fun postUserMessage(message: String, user: String) {
        dbHelper.postUserMessage(message, user)
    }

    override fun postMessageQueryToAI(message: String) {
        aiHelper.postMessageQueryToAI(message)
    }

    override fun postBotMessage(botMessage: String) {
        dbHelper.postBotMessage(botMessage)
    }

    // *********************** AI **********************************

    override fun makeAIRequest(): Observable<AIResponse> {
        return aiHelper.makeAIRequest()
    }

    override fun startAiService() {
        aiHelper.startAiService()
    }

    override fun stopAiService() {
        aiHelper.stopAiService()
    }
}