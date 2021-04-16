package com.junxin.freepaykotlin.fragment

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import com.junxin.freepaykotlin.R
import com.junxin.freepaykotlin.adapter.MyBannerAdapter
import com.junxin.freepaykotlin.api.NetWork
import com.junxin.freepaykotlin.api.callback.ApiErr
import com.junxin.freepaykotlin.api.callback.ApiSuccess
import com.junxin.freepaykotlin.bean.BannerBean
import com.junxin.freepaykotlin.utils.ToastUtil
import com.youth.banner.indicator.RectangleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.ResponseBody
import retrofit2.Call
import kotlin.math.abs

class HomeFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_home

    override fun init() {
        initBar()

        home_rv.layoutManager = LinearLayoutManager(activity)
        home_rv.setItemViewCacheSize(8)

        initRefresh()

    }

    private fun initRefresh(){
        home_smart.setEnableAutoLoadMore(false)
        home_smart.setEnableLoadMore(false)
        home_smart.setOnRefreshListener {
            it.finishRefresh(1000)
        }


    }

    lateinit var state : CollapsingToolbarLayoutState

    enum class CollapsingToolbarLayoutState {
        EXPANDED, COLLAPSED, INTERNEDIATE
    }

    private fun initBar(){
        app_bar.addOnOffsetChangedListener(object :AppBarLayout.OnOffsetChangedListener{
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if(verticalOffset == 0) {
                    if(state != CollapsingToolbarLayoutState.EXPANDED){
                        state = CollapsingToolbarLayoutState.EXPANDED //修改状态标记为展开
                    }else if(abs(verticalOffset) >= appBarLayout!!.totalScrollRange){
                        if(state != CollapsingToolbarLayoutState.COLLAPSED){
                            tv2.visibility = View.VISIBLE
                            state = CollapsingToolbarLayoutState.COLLAPSED
                        }
                    }else{
                        if(state != CollapsingToolbarLayoutState.INTERNEDIATE){
                            if(state == CollapsingToolbarLayoutState.COLLAPSED){
                                tv2.visibility = View.GONE
                            }
                            state = CollapsingToolbarLayoutState.INTERNEDIATE
                        }
                    }
                }
            }

        })
    }

    private fun initBanner(){
        NetWork.getInstance()?.getBanner(object : ApiSuccess{
            override fun onResponse(call: Call<ResponseBody>?, json: String?) {
                var bannerBean = Gson().fromJson(json,BannerBean::class.java)
                if(bannerBean.code != 0){
                    ToastUtil.showShortToastCenter(bannerBean.msg)
                    return
                }
                val data = bannerBean.data

                home_banner.addBannerLifecycleObserver(activity)
                    .setAdapter(MyBannerAdapter(data))
                    .setIndicator(RectangleIndicator(activity))
                    .setIndicatorSelectedWidth(12)
                    .setIndicatorSpace(5)
                    .setIndicatorRadius(5)

            }

        },object : ApiErr{
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}