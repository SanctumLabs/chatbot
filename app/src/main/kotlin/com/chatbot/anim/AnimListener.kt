package com.chatbot.anim

/**
 * @author lusinabrian on 16/11/17.
 * @Notes
 */
interface AnimListener {
    /*
    * hits when Spring is Active
    * */
    fun onSpringStart()

    /*
    * hits when Spring is inActive
    * */

    fun onSpringStop()
}