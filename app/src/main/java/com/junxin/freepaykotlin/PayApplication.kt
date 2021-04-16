package com.junxin.freepaykotlin

import android.app.Application

class PayApplication : Application() {
    companion object{
        var instance:PayApplication? =null
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}