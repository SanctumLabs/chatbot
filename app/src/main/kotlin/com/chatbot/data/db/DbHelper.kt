package com.chatbot.data.db

/**
 * @author lusinabrian on 23/09/17.
 * @Notes Helper interface
 */
interface DbHelper {

    /**
     * Sends a chat message to the database
     * @param message String message from user
     * @param user the user sending the message
     * */
    fun postUserMessage(message: String, user : String = "user")

    /**
     * Posts Bot message to db
     * @param botMessage the bot message to post to db
     * */
    fun postBotMessage(botMessage : String)
}