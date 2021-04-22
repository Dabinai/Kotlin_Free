package com.junxin.freepaykotlin.utils

import android.content.Context
import android.util.TypedValue

/*
 * ：Created by z on 2020-08-06
 */ /**
 * 像素转化
 */
object DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    //    public static int dip2px(Context context, float dpValue) {
    //        final float scale = context.getResources().getDisplayMetrics().density;
    //        return (int) (dpValue * scale + 0.5f);
    //    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    //    public static int sp2px(Context context, float spValue) {
    //        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    //        return (int) (spValue * fontScale + 0.5f);
    //    }
    @JvmStatic
    fun sp2px(context: Context, spValue: Int): Int {
        val pxDimension = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_PX,
            spValue.toFloat(),
            context.resources.displayMetrics
        )
        return pxDimension.toInt()
    }
    @JvmStatic
    fun dip2px(context: Context, dp: Int): Int {
        val pxDimension = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        )
        return pxDimension.toInt()
    }
}