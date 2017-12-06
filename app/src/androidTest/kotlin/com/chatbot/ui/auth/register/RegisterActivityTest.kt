package com.chatbot.ui.auth.register

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.chatbot.R
import org.junit.*
import org.junit.runner.RunWith

/**
 * @author lusinabrian on 06/12/17.
 * @Notes [RegisterActivity] Instrumented Test
 */
@RunWith(AndroidJUnit4::class)
@MediumTest
class RegisterActivityTest {

    companion object {
        @Rule
        @ClassRule
        @JvmStatic
        val activityTestRule : ActivityTestRule<RegisterActivity> = ActivityTestRule(RegisterActivity::class.java)
    }

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
    }

    @Test
    fun testRegisterLabelIsVisible(){
        onView(withId(R.id.registerLabelTxt)).check(matches(isDisplayed()))
    }

    @Test
    fun testMismatchInPasswordsGiveErrors(){
        // username
        onView(withId(R.id.registerUsernameEdtTxt)).perform(typeText("username"))
                .check(matches(isDisplayed()))

        // email
        onView(withId(R.id.registerEmailEdtTxt)).perform(typeText("email@com"))
                .check(matches(isDisplayed()))

        // password
        onView(withId(R.id.registerPasswordEdtTxt)).perform(typeText("password1"))
                .check(matches(isDisplayed()))

        // retype password
        onView(withId(R.id.registerRepeatPasswordEdtTxt)).perform(typeText("password2"))
                .check(matches(isDisplayed()))

        // attempt login
        onView(withId(R.id.registerButton)).perform(click())

        // check that errors are displayed
        // email error
        onView(withId(R.id.registerEmailEdtTxt))
                .check(matches(hasErrorText("Enter valid email address")))

        /*check for invalid password error*/
        onView(withId(R.id.registerPasswordEdtTxt))
                .check(matches(hasErrorText("Passwords must match")))

        onView(withId(R.id.registerRepeatPasswordEdtTxt))
                .check(matches(hasErrorText("Passwords must match")))
    }

}