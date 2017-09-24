package com.chatbot.data

import com.chatbot.data.io.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/**
 * @author lusinabrian on 12/06/17.
 * @Notes Implementation of test scheduler as used in application
 * This will implement the SchedulerProvider and will be used in tests to mock the behaviour
 * of the Scheduler provider in the application
 */
class TestSchedulerProvider(val mTestScheduler: TestScheduler) : SchedulerProvider {
    override fun ui(): Scheduler {
        return mTestScheduler
    }

    override fun computation(): Scheduler {
        return mTestScheduler
    }

    override fun io(): Scheduler {
        return mTestScheduler
    }

    override fun newThread(): Scheduler {
        return mTestScheduler
    }

    override fun trampoline(): Scheduler {
        return mTestScheduler
    }

    override fun start() {
        mTestScheduler
    }

    override fun shutdown() {
        mTestScheduler
    }

    override fun single(): Scheduler {
        return mTestScheduler
    }
}