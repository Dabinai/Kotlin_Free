package com.junxin.freepaykotlin.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.junxin.freepaykotlin.R
import com.junxin.freepaykotlin.adapter.DailyAdapter
import com.junxin.freepaykotlin.api.NetWork
import com.junxin.freepaykotlin.api.callback.ApiErr
import com.junxin.freepaykotlin.api.callback.ApiSuccess
import com.junxin.freepaykotlin.bean.DailyBean
import com.junxin.freepaykotlin.bean.DisDataBean
import com.junxin.freepaykotlin.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_daily_son.*
import okhttp3.ResponseBody
import retrofit2.Call

class DailyFragment : BaseFragment() {

    private var curStatus: Int = 0
    private var nextPage = 1
    private var dailyBean : DailyBean? = null
    private var dailyAdapter : DailyAdapter? = null
    private var totalY = 0
    override fun getLayout(): Int = R.layout.fragment_daily_son

    companion object {
        fun newInstance(curStatus: Int): DailyFragment {
            var fragmentOne = DailyFragment()
            var bundle = Bundle()
            bundle.putInt("curStatus", curStatus)
            fragmentOne.arguments = bundle
            return fragmentOne
        }
    }

    override fun init() {

        curStatus = requireArguments().getInt("curStatus")


    }

    private fun requestUiList(curStatus: Int) {
        var map = HashMap<String, Any>()
        map.put("status", curStatus.toString())
        map.put("page", curStatus.toString())
        map.put("status", curStatus.toString())

        NetWork.getInstance()?.getArticle(map as Map<String?, String?>, object : ApiSuccess {
            override fun onResponse(call: Call<ResponseBody>?, json: String?) {
                var daily = Gson().fromJson(json, DailyBean::class.java)
                if (daily.code == 0) {
                    if(nextPage > 1){
                        var disDataBean = daily.data?.data
                        dailyBean?.data?.data?.addAll(disDataBean!!)
                        dailyBean?.data!!.total  += daily.data?.data?.size!!
                        refreshRv(daily.data!!,false)
                        nextPage ++
                        return
                    }
                    dailyBean = daily
                    nextPage++
                    refreshRv(dailyBean?.data!!,false)
                }
            }

        }, object : ApiErr {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable) {
                ToastUtil.showShortToastCenter("数据错误")
            }

        })
    }


    private fun refreshRv(dataBeanX: DailyBean.DataBeanX,isAddNew:Boolean){
        if(dataBeanX.data == null || dataBeanX!!.data!!.size <= 0){
            daily_fragment_tip.visibility = View.VISIBLE
            daily_fragment_rv.visibility = View.GONE
            return
        }
        daily_fragment_rv.visibility = View.VISIBLE
        dailyAdapter = DailyAdapter(requireActivity(),dataBeanX.data!!)
        if(isAddNew){
            dailyAdapter?.addMore(dataBeanX.data)
            return

        }
        daily_fragment_rv.layoutManager = LinearLayoutManager(activity)
        daily_fragment_rv.adapter = dailyAdapter

        dailyAdapter?.setOnItemClick(object : DailyAdapter.ItemClick{
            override fun onClick(position: Int) {


            }

        })

        daily_fragment_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val lm = recyclerView.layoutManager as LinearLayoutManager
                var totalItemCount = recyclerView.adapter?.itemCount
                var lastVisibleItemPosition = lm!!.findLastVisibleItemPosition()
                var visibleItemCount = recyclerView.childCount

                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition == totalItemCount!! - 1 && visibleItemCount > 0){
                    loadMore()
                }

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalY += dy
            }
        })

    }

    private fun loadMore(){
        if(this.dailyBean!!.data!!.data!!.size >= this.dailyBean!!.data!!.total){
            return
        }
        requestUiList(curStatus)
    }


}