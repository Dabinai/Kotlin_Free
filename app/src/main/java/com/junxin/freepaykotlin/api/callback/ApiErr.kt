package com.junxin.freepaykotlin.api.callback

import okhttp3.ResponseBody
import retrofit2.Call

interface ApiErr {
    fun onFailure(call:Call<ResponseBody>?,t: Throwable)
}