package com.junxin.freepaykotlin.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.junxin.freepaykotlin.R
import com.junxin.freepaykotlin.widget.GlideCircleWithBorder
import java.io.File

/*
 * ：Created by z on 2020/10/10
 */   class GlideTool {
    /**
     * 圆角头像
     * @param context
     * @param img
     * @param url
     */
    fun setAvatar(
        context: Context,
        img: ImageView?,
        url: String?
    ) {
        val mRequestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE) //不做磁盘缓存
            .transform(
                GlideCircleWithBorder(
                    context,
                    2,
                    context.resources.getColor(R.color.app_color_white)
                )
            )
            .skipMemoryCache(true) //不做内存缓存
        Glide.with(context).load(url).apply(mRequestOptions).placeholder(R.mipmap.user_defult2)
            .into(img!!)
    }

    /**
     * 圆角头像
     * @param context
     * @param img
     */
    fun setAvatar(
        context: Context,
        img: ImageView?,
        file: File?
    ) {
        val mRequestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE) //不做磁盘缓存
            .transform(
                GlideCircleWithBorder(
                    context,
                    2,
                    context.resources.getColor(R.color.app_color_white)
                )
            )
            .skipMemoryCache(true) //不做内存缓存
        Glide.with(context).load(file).apply(mRequestOptions).placeholder(R.mipmap.user_defult2)
            .into(img!!)
    }

    fun radius(
        context: Context?,
        img: ImageView?,
        url: String?,
        radius: Int
    ) {
        //设置图片圆角角度
        val roundedCorners = RoundedCorners(radius)
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        // RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        val options = RequestOptions.bitmapTransform(roundedCorners)
        Glide.with(context!!).load(url).apply(options).into(img!!)
    }

    fun getBitmap(
        context: Context?,
        url: String?,
        sucess: GlideSucess?,
        err: GlideErr?
    ) {
        Glide.with(context!!)
            .asBitmap()
            .load(url)
            .skipMemoryCache(true)
            .into(object : SimpleTarget<Bitmap?>() {
                override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap?>?) {
                    sucess?.onResourceReady(bitmap)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    err?.onLoadErr(Exception("err"))
                }
            }
            )
    }

    fun getBitmap(
        context: Context?,
        url: String?,
        sucess: GlideSucess?
    ) {
        this.getBitmap(context, url, sucess, null)
    }

    interface GlideSucess {
        fun onResourceReady(bitmap: Bitmap?)
    }

    interface GlideErr {
        fun onLoadErr(e: Exception?)
    }

    companion object {
        var instance: GlideTool? = null
            get() {
                if (field == null) {
                    field = GlideTool()
                }
                return field
            }
            private set
    }
}