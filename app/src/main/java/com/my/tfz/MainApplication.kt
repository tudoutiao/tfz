package com.my.tfz

import android.app.Application
import android.content.Context
import androidx.viewbinding.BuildConfig
import androidx.work.Configuration

open class MainApplication : Application(), Configuration.Provider {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=this
    }



    override fun getWorkManagerConfiguration(): Configuration = Configuration.Builder()
        .setMinimumLoggingLevel(if (BuildConfig.DEBUG) android.util.Log.DEBUG else android.util.Log.ERROR)
        .build()

}