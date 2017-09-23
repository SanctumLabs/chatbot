package com.chatbot.data.ai

import ai.api.AIDataService
import ai.api.android.AIService
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import io.reactivex.Observable
import io.reactivex.Observer
import org.jetbrains.anko.doAsync
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

    // todo: make ai request in background and return result
    override fun makeAIRequest(): Observable<AIResponse> {
        var aiResponse: Observable<AIResponse> = object : Observable<AIResponse>() {
            override fun subscribeActual(observer: Observer<in AIResponse>?) {

            }
        }
        doAsync {
            aiResponse = Observable.just(aiDataService.request(aiRequest))
            println("doAsync => $aiResponse")
        }
        println("doAsync => $aiResponse")
        return aiResponse
    }
}