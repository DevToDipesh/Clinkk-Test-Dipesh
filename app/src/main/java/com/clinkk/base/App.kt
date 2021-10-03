package com.clinkk.base

import android.app.Application

class App: Application() {
    companion object{

        lateinit var instance: App
        @JvmStatic
        fun get(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}