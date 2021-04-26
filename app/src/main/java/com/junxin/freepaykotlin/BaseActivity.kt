package com.junxin.freepaykotlin

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.junxin.freepaykotlin.utils.NavigationbarUtil
import com.junxin.freepaykotlin.utils.StatusBarUtil

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavigationbarUtil.instance!!.setActivty2Up(this)
        StatusBarUtil.StatusBarLightMode(this)
        var layoutResID = getLayout()
        setContentView(layoutResID)
        onInit()
    }

    abstract fun getLayout():Int
    abstract fun onInit()
}