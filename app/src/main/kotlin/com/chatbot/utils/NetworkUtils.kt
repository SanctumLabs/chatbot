@file:JvmName("NetworkUtils")
package com.chatbot.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * @author lusinabrian on 17/01/18.
 * @Notes Network Utilties
 */

/**
 * Checks if the network is connected*/
fun isNetworkConnected(context: Context) : Boolean{
    val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
}