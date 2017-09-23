package com.chatbot.data.db

import com.chatbot.di.qualifier.DatabaseRefQualifier
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author lusinabrian on 23/09/17.
 * @Notes db implementation for the database
 */
@Singleton
class DbHelperImpl
@Inject
constructor(@DatabaseRefQualifier val databaseReference: DatabaseReference) : DbHelper {


}