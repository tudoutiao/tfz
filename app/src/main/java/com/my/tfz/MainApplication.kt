package com.my.tfz

import android.app.Application
import androidx.viewbinding.BuildConfig
import androidx.work.Configuration

class MainApplication : Application(), Configuration.Provider {
    override fun getWorkManagerConfiguration(): Configuration = Configuration.Builder()
        .setMinimumLoggingLevel(if (BuildConfig.DEBUG) android.util.Log.DEBUG else android.util.Log.ERROR)
        .build()

}