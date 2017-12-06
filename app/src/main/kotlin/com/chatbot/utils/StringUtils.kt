@file:JvmName("StringUtils")
package com.chatbot.utils

import android.text.TextUtils
import android.util.Patterns

/**
 * @author lusinabrian on 06/12/17.
 * @Notes String util methods
 */

/**
 * Checks if the Email is valid*/
fun isEmailValid(email : String ) = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()

/**
 * Validates the password and repeat password
 * @param password1 Password 1
 * @param password2 Password 2
 * @return [Boolean] True if passwords match
 * */
fun validatePasswords(password1: String, password2: String) : Boolean{
    if(TextUtils.isEmpty(password1)|| TextUtils.isEmpty(password2)){
        return false
    }

    // if the passwords do not match
    else if(password2 != password1){
        return false
    }
    return true
}

