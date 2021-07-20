package com.koc.blogg

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp

/**
Created by kelvin_clark on 7/15/2021 1:25 AM
 */
@HiltAndroidApp
class BloggApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())
        }
    }
}