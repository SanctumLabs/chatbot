package com.chatbot.data.io

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler

    fun newThread(): Scheduler

    fun trampoline(): Scheduler

    fun start()

    fun shutdown()

    fun single(): Scheduler
}
