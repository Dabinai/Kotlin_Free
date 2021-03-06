package com.junxin.freepaykotlin.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import com.junxin.freepaykotlin.MainActivity
import com.junxin.freepaykotlin.R
import com.junxin.freepaykotlin.adapter.HomeAdapter
import com.junxin.freepaykotlin.adapter.MyBannerAdapter
import com.junxin.freepaykotlin.api.NetWork
import com.junxin.freepaykotlin.api.callback.ApiErr
import com.junxin.freepaykotlin.api.callback.ApiSuccess
import com.junxin.freepaykotlin.bean.BannerBean
import com.junxin.freepaykotlin.bean.HomeTaskBean
import com.junxin.freepaykotlin.bean.MineTaskBean
import com.junxin.freepaykotlin.bean.TaskCommonBean
import com.junxin.freepaykotlin.utils.ToastUtil
import com.youth.banner.indicator.RectangleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.ResponseBody
import retrofit2.Call
import kotlin.math.abs

class HomeFragment : BaseFragment() {

    var listData = mutableListOf<TaskCommonBean>()
    var isUserTak = false
    var currentPage = 1;
    var homeAdapter: HomeAdapter? =null
    override fun getLayout(): Int = R.layout.fragment_home

    override fun init() {
        initBar()

        home_rv.layoutManager = LinearLayoutManager(activity)
        home_rv.setItemViewCacheSize(8)

        initRefresh()
        initBanner()
        initUserTask()
    }

    private fun initRefresh() {
        home_smart.setEnableAutoLoadMore(false)
        home_smart.setEnableLoadMore(false)
        home_smart.setOnRefreshListener {
            it.finishRefresh(1000)
            initUserTask()
        }
        home_smart.setOnLoadMoreListener{
            it.finishLoadMore(1000)
            currentPage++
            initHomeRv()
        }


    }

     var state: CollapsingToolbarLayoutState = CollapsingToolbarLayoutState.EXPANDED

    enum class CollapsingToolbarLayoutState {
        EXPANDED, COLLAPSED, INTERNEDIATE
    }

    private fun initBar() {
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED //???????????????????????????
                    } else if (abs(verticalOffset) >= appBarLayout!!.totalScrollRange) {
                        if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                            tv2.visibility = View.VISIBLE
                            state = CollapsingToolbarLayoutState.COLLAPSED
                        }
                    } else {
                        if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                            if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                                tv2.visibility = View.GONE
                            }
                            state = CollapsingToolbarLayoutState.INTERNEDIATE
                        }
                    }
                }
            }

        })
    }

    private fun initBanner() {
        NetWork.getInstance()?.getBanner(object : ApiSuccess {
            override fun onResponse(call: Call<ResponseBody>?, json: String?) {
                var bannerBean = Gson().fromJson(json, BannerBean::class.java)
                if (bannerBean.code != 0) {
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

                home_banner.setOnBannerListener({ data, position ->
                    ToastUtil.showShortToastCenter("${position}")
                })

            }

        }, object : ApiErr {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable) {
                ToastUtil.showShortToastCenter(t.toString())
            }

        })
    }

    fun initUserTask() {
        listData.clear()
        isUserTak = false
        NetWork.getInstance()?.getUserList(object : ApiSuccess {
            override fun onResponse(call: Call<ResponseBody>?, json: String?) {
                var mineTaskBean = Gson().fromJson(json, MineTaskBean::class.java)
                if (mineTaskBean.code == 0) {
                    var data = mineTaskBean.data?.data
                    if (data?.size == 1) {
                        var taskCommonBean = data.get(0)
                        taskCommonBean.type = 2
                        var userTaskList = mutableListOf<TaskCommonBean>()

                        var taskCommonBean1 = TaskCommonBean()
                        taskCommonBean1.title = taskCommonBean.title
                        taskCommonBean1.subTitle = taskCommonBean.subTitle
                        taskCommonBean1.cover = taskCommonBean.cover
                        taskCommonBean1.price = taskCommonBean.price
                        taskCommonBean1.mpAppId = taskCommonBean.mpAppId

                        userTaskList.add(taskCommonBean1)

                        taskCommonBean.userTaskBeanList = userTaskList
                    }else if(data?.size!! > 1){
                        var userTaskList = mutableListOf<TaskCommonBean>()

                        var taskCommonBeanOne = data.get(0)
                        taskCommonBeanOne.type = 2
                        var taskCommonBean1 = TaskCommonBean()
                        taskCommonBean1.title = taskCommonBeanOne.title
                        taskCommonBean1.subTitle = taskCommonBeanOne.subTitle
                        taskCommonBean1.cover = taskCommonBeanOne.cover
                        taskCommonBean1.price = taskCommonBeanOne.price
                        taskCommonBean1.mpAppId = taskCommonBeanOne.mpAppId
                        userTaskList.add(taskCommonBean1)

                        var taskCommonBeanTwo = data.get(1)
                        taskCommonBeanTwo.type = 2
                        var taskCommonBean2 = TaskCommonBean()
                        taskCommonBean2.title = taskCommonBeanTwo.title
                        taskCommonBean2.subTitle = taskCommonBeanTwo.subTitle
                        taskCommonBean2.cover = taskCommonBeanTwo.cover
                        taskCommonBean2.price = taskCommonBeanTwo.price
                        taskCommonBean2.mpAppId = taskCommonBeanTwo.mpAppId
                        userTaskList.add(taskCommonBean2)

                        taskCommonBeanOne.userTaskBeanList = userTaskList
                        listData.add(taskCommonBeanOne)


                    }else{
                        ToastUtil.showShortToastCenter(mineTaskBean.msg)
                    }
                    initHomeRv()
                }
            }

        }, object : ApiErr {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable) {
                initHomeRv()
                ToastUtil.showShortToast(t.toString())
            }

        })

    }

    fun initHomeRv(){
        NetWork.getInstance()?.getHomeList("${currentPage}",object : ApiSuccess{
            override fun onResponse(call: Call<ResponseBody>?, json: String?) {
                var homeTaskBean = Gson().fromJson(json,HomeTaskBean::class.java)
                if(homeTaskBean.code != 0){
                    ToastUtil.showShortToastCenter(homeTaskBean.msg)
                    return
                }
                var taskCommonBean = TaskCommonBean()
                taskCommonBean.type = 1
                listData.add(listData.size,taskCommonBean)
                homeTaskBean.data?.data?.let { listData.addAll(it) }

                if(homeAdapter == null){
                    homeAdapter = activity?.let { HomeAdapter(it,listData,isUserTak) }
                    home_rv.adapter = homeAdapter
                }else{
                    homeAdapter?.setData(listData,isUserTak)
                }
                homeAdapter?.setOnItemClick(object : HomeAdapter.ItemClick{
                    override fun onClick(position: Int, appId: String?) {


                    }

                    override fun onSkip() {
                        (activity as MainActivity).changeFragment(2)

                    }

                })


            }

        },object : ApiErr{
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable) {
                ToastUtil.showShortToastCenter(t.toString())
            }

        })
    }

}