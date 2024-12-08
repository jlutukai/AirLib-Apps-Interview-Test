package com.airlibs.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AirLibsTest : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }


    private fun initTimber() {
        Timber.plant()
    }
}