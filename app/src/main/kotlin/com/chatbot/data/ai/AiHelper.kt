package com.chatbot.data.ai

import ai.api.model.AIRequest
import ai.api.model.AIResponse
import io.reactivex.Observable

/**
 * @author lusinabrian on 23/09/17.
 * @Notes Ai Helper interface will be used to communicate directly with AI service
 */
interface AiHelper{

    /**
     * Starts AI service in application
     * */
    fun startAiService()

    /**
     * Stops the currently running AI Service
     * */
    fun stopAiService()

    /**
     * post message query to AI
     * @param message Message to post to AI
     * */
    fun postMessageQueryToAI(message : String)

    /**
     * Makes an AI request with [AIRequest] and awaits response from AI
     * @return [Observable] an observable with the response from the AI
     * */
    fun makeAIRequest() : Observable<AIResponse>
}