package com.chatbot.data.ai

import ai.api.AIDataService
import ai.api.android.AIService
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import io.reactivex.Observable
import org.jetbrains.anko.AnkoAsyncContext
import org.jetbrains.anko.doAsyncResult
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author lusinabrian on 23/09/17.
 * @Notes AI Helper implementation
 */
@Singleton
class AIHelperImpl
@Inject
constructor(@Named("AiService") val aiService: AIService,
            @Named("AiRequest") val aiRequest: AIRequest,
            @Named("AiDataService") val aiDataService: AIDataService) : AiHelper {

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
        val aiResponseTask: (AnkoAsyncContext<Any>.() -> Observable<AIResponse>) = {
            Observable.just(aiDataService.request(aiRequest))
        }

        val aiResult = doAsyncResult({
            error("Error encountered fetching AI response with ${it.message}")
        }, aiResponseTask)

        return aiResult.get()
    }
}