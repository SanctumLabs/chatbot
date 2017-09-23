package com.chatbot.di.modules

import com.chatbot.data.db.DbHelper
import com.chatbot.data.db.DbHelperImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides

/**
 * @author lusinabrian on 23/09/17.
 * @Notes Database module for providing database dependencies
 */
@Module
class DatabaseModule {

    @Provides
    fun provideDbHelper(dbHelper: DbHelperImpl) : DbHelper{
        return dbHelper
    }

    @Provides
    fun provideFirebaseDatabase(): DatabaseReference {
        val dbRef = FirebaseDatabase.getInstance().reference
        dbRef.keepSynced(true)
        return dbRef
    }
}