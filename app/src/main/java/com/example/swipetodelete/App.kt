package com.example.swipetodelete

import android.app.Application
import timber.log.BuildConfig
import timber.log.Timber

/*
Created by ​
Hannure Abdulrahim


on 10/20/2021.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}