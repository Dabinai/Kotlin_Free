package com.junxin.freepaykotlin

import android.app.Application
import com.junxin.freepaykotlin.utils.SPUtil

class PayApplication : Application() {
    companion object{
        var instance:PayApplication? =null
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        SPUtil.ini(this)
    }
}