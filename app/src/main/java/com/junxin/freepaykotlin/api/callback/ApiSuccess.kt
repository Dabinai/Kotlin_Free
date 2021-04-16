package com.junxin.freepaykotlin.api.callback

import okhttp3.ResponseBody
import org.json.JSONException
import retrofit2.Call

interface ApiSuccess {
    @Throws(JSONException::class)
    fun onResponse(call: Call<ResponseBody>?, json: String?)
}