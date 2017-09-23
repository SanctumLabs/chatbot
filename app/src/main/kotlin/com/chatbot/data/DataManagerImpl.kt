package com.chatbot.data

import com.chatbot.data.db.DbHelper
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author lusinabrian on 23/09/17.
 * @Notes Implementation for the datamanager
 */
@Singleton
class DataManagerImpl
@Inject
constructor(val dbHelper: DbHelper): DataManager{

}