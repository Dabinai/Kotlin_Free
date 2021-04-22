package com.junxin.freepaykotlin.widget

import android.os.Handler
import android.view.View

abstract class SingleClickListener: View.OnClickListener{
    var delayTime : Long = 1500;

    constructor()

    constructor(delayTime:Long) {
        this.delayTime = delayTime
    }

    override fun onClick(v: View) {
        this.onSingleClick(v)
        v.isClickable =  false
        Handler().postDelayed({ v.isClickable = true },delayTime)
    }
    abstract fun onSingleClick(v:View?)

}