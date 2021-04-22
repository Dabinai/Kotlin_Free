package com.junxin.freepaykotlin.fragment

import android.os.Bundle
import com.google.gson.Gson
import com.junxin.freepaykotlin.R
import com.junxin.freepaykotlin.api.NetWork
import com.junxin.freepaykotlin.api.callback.ApiErr
import com.junxin.freepaykotlin.api.callback.ApiSuccess
import com.junxin.freepaykotlin.bean.DailyBean
import okhttp3.ResponseBody
import retrofit2.Call

class DailyFragment : BaseFragment() {

    private var curStatus: Int = 0
    private var nextPage = 1;
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

                }
            }

        }, object : ApiErr {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable) {

            }

        })
    }


}