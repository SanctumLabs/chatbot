package com.chatbot.data.ai

import ai.api.AIDataService
import ai.api.AIServiceException
import ai.api.android.AIService
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import android.os.AsyncTask
import com.chatbot.ui.ChatMessage
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author lusinabrian on 23/09/17.
 * @Notes AI Helper implementation
 */
@Singleton
class AIHelperImpl
@Inject
constructor(val aiService: AIService, val aiRequest: AIRequest,
            val aiDataService: AIDataService) : AiHelper {

    override fun startAiService() {
        aiService.startListening()
    }

    override fun stopAiService() {
        aiService.stopListening()
    }

    override fun postMessageQueryToAI(message: String) {
        aiRequest.setQuery(message)
    }

    override fun makeAIRequest(): Observable<AIResponse> {
        return Observable.just(aiDataService.request(aiRequest))
    }
}