package com.junxin.freepaykotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.scwang.smart.drawable.ProgressDrawable
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshKernel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.scwang.smart.refresh.layout.util.SmartUtil

/*
 * ：Created by z on 2020/11/10
 */   class SimpleHeader : LinearLayout, RefreshHeader {
    private var mHeaderText //标题文本
            : TextView? = null

    //    private PathsView mArrowView;//下拉箭头
    private var mProgressView //刷新动画视图
            : ImageView? = null
    private var mProgressDrawable //刷新动画
            : ProgressDrawable? = null

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        initView(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        gravity = Gravity.CENTER
        mHeaderText = TextView(context)
        mProgressDrawable = ProgressDrawable()
        mProgressView = ImageView(context)
        mProgressView!!.setImageDrawable(mProgressDrawable)
        addView(mProgressView, SmartUtil.dp2px(20f), SmartUtil.dp2px(20f))
        addView(View(context), SmartUtil.dp2px(20f), SmartUtil.dp2px(20f))
        addView(
            mHeaderText,
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        this.minimumHeight = SmartUtil.dp2px(30f)
    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun onStartAnimator(
        refreshLayout: RefreshLayout,
        height: Int,
        maxDragHeight: Int
    ) {
        mProgressDrawable!!.start() //开始动画
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        mProgressDrawable!!.stop() //停止动画
        if (success) {
            mHeaderText!!.text = "刷新完成"
        } else {
            mHeaderText!!.text = "刷新失败"
        }
        return 500 //延迟500毫秒之后再弹回
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        when (newState) {
            RefreshState.None, RefreshState.PullDownToRefresh -> {
                mHeaderText!!.text = "下拉开始刷新"
                // mArrowView.setVisibility(VISIBLE);//显示下拉箭头
                mProgressView!!.visibility = View.GONE //隐藏动画
            }
            RefreshState.Refreshing -> {
                mHeaderText!!.text = "正在刷新"
                mProgressView!!.visibility = View.VISIBLE //显示加载动画
            }
            RefreshState.ReleaseToRefresh -> mHeaderText!!.text = "释放立即刷新"
        }
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    override fun setPrimaryColors(vararg colors: Int) {}
    override fun onInitialized(
        kernel: RefreshKernel,
        height: Int,
        maxDragHeight: Int
    ) {
    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
    }

    override fun onReleased(
        refreshLayout: RefreshLayout,
        height: Int,
        maxDragHeight: Int
    ) {
    }

    override fun onHorizontalDrag(
        percentX: Float,
        offsetX: Int,
        offsetMax: Int
    ) {
    }
}