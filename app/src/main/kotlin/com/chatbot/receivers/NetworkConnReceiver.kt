package com.chatbot.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.chatbot.utils.isNetworkConnected

class NetworkConnReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.net.conn.CONNECTIVITY_CHANGE") {
            if (isNetworkConnected(context)) {
                // post event to app that app is connected
            } else {
                // d
            }
        }
    }
}
