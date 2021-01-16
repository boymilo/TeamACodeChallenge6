package com.teamacodechallenge6

import android.app.Application
import android.content.Context
import com.teamacodechallenge6.data.database.TemanDatabase

class App: Application() {
    companion object{
        var context: Context? = null
        var mDB: TemanDatabase?=null
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}