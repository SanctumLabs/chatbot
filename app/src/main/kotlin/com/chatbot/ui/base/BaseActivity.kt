package com.chatbot.ui.base

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.chatbot.R
import com.chatbot.app.ChatBotApp
import com.chatbot.di.components.ActivityComponent
import com.chatbot.di.components.AppComponent
import com.chatbot.di.components.DaggerActivityComponent
import com.chatbot.di.modules.ActivityModule
import org.jetbrains.anko.AnkoLogger

/**
 * @author lusinabrian on 10/06/17.
 * *
 * @Notes
 */

abstract class BaseActivity : AppCompatActivity(), BaseView, BaseFragment.Callback, AnkoLogger {

    // fields
    lateinit var activityComponent: ActivityComponent

    val appComponent : AppComponent by lazy { (application as ChatBotApp).appComponent }

    override val loggerTag: String
        get() = super.loggerTag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent(appComponent)
                .build()
    }

    override fun onResume() {
        super.onResume()

    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * before destroying the view, do a sanity check of the view bindings before destroying the
     * activity  */
    public override fun onDestroy() {
        super.onDestroy()
    }

    override fun setPasswordError(errorMessage: Int) {
        setPasswordError(getString(errorMessage))
    }

    override fun setPasswordError(errorMessage: String) {}

    override fun setUsernameError(errorMessage: String) {}

    override fun setUsernameError(errorMessage: Int) {
        setUsernameError(getString(errorMessage))
    }

    /**
     * Hides keyboard
     */
    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun displaySnackbar(message: String, length: Int, rootLayout: Int, networkError: Boolean) {
        val snackbar = Snackbar.make(findViewById<View>(rootLayout), message, length)

        // if this is a network error, display the snackbar action
        if (networkError) {
            snackbar.setAction(R.string.action_snackbar_dismiss) {
                if (snackbar.isShown) {
                    snackbar.dismiss()
                }
            }
        }
        val view = snackbar.view
        val textView = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, R.color.color_white))
        snackbar.show()
    }

    override fun displaySnackbar(message: Int, length: Int, rootLayout: Int, networkError: Boolean) {
        displaySnackbar(getString(message), length, rootLayout, networkError)
    }

    /**
     * Callback for when a fragment is attached to an activity
     */
    override fun onFragmentAttached() {

    }

    /**
     * Callback for when a fragment is detached from an activity

     * @param tag the fragment tag to detach
     */
    override fun onFragmentDetached(tag: String) {

    }
}
