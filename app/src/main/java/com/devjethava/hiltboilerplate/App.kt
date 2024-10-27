package com.devjethava.hiltboilerplate

import androidx.multidex.MultiDexApplication
import com.devjethava.hiltboilerplate.helper.Utils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : MultiDexApplication() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        Utils.printLog("APP", " -> Application onCreate")
    }

    override fun onTerminate() {
        super.onTerminate()
        Utils.printLog("APP", " -> Application onTerminate")
    }
}