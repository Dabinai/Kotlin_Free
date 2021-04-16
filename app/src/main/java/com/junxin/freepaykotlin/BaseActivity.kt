package com.junxin.freepaykotlin

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        var layoutResID = getLayout()
        setContentView(layoutResID)
        onInit()
    }

    abstract fun getLayout():Int
    abstract fun onInit()
}