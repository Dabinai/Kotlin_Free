package com.junxin.freepaykotlin.utils

import android.R
import android.app.Activity
import android.view.*

/*
 * ：Created by z on 2020-09-08
 */   class NavigationbarUtil {
    fun setActivty2Up(activity: Activity) {
        if (isNavigationBarExist(activity)) {
            activity.window.decorView
                .findViewById<View>(R.id.content)
                .setPadding(0, 0, 0, getNavigationBarHeight(activity))
        }
    }

    private fun getNavigationBarHeight(activity: Activity): Int {
        val hasMenuKey = ViewConfiguration.get(activity).hasPermanentMenuKey()
        val hasBackKey =
            KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
        return if (!hasMenuKey && !hasBackKey) {
            val resources = activity.resources
            val resourceId =
                resources.getIdentifier("navigation_bar_height", "dimen", "android")
            //获取NavigationBar的高度
            resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }

    companion object {
        var instance: NavigationbarUtil? = null
            get() {
                if (field == null) {
                    field = NavigationbarUtil()
                }
                return field
            }
            private set
        private const val NAVIGATION = "navigationBarBackground"

        // 该方法需要在View完全被绘制出来之后调用，否则判断不了
        //在比如 onWindowFocusChanged（）方法中可以得到正确的结果
        fun isNavigationBarExist(activity: Activity): Boolean {
            val vp = activity.window.decorView as ViewGroup
            if (vp != null) {
                for (i in 0 until vp.childCount) {
                    vp.getChildAt(i).context.packageName
                    if (vp.getChildAt(i)
                            .id != View.NO_ID && NAVIGATION == activity.resources
                            .getResourceEntryName(vp.getChildAt(i).id)
                    ) {
                        return true
                    }
                }
            }
            return false
        }
    }
}