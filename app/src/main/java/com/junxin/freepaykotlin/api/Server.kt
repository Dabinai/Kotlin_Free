package com.junxin.freepaykotlin.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Server {


    @POST("/passport/mobile/code")
    fun getMoboileCode(@Body bodyMap: Map<String?, String?>?): Call<ResponseBody>?

    @POST("/passport/mobile/login")
    fun getMobileCodeLogin(@Body bodyMap: Map<String?,String?>?):Call<ResponseBody>?

    @POST("/passport/weixin/code")
    fun getWxMoboileCode(@Body bodyMap: Map<String?, String?>?): Call<ResponseBody>?

    @POST("/weixin/login")
    fun getWxLogin(@Body bodyMap: Map<String?, String?>?): Call<ResponseBody>?

    @POST("/passport/user/login")
    fun getUserLogin(@Body bodyMap: Map<String?, String?>?): Call<ResponseBody>?


    /**
     * 官方活动
     * @return
     */
    @GET("/activity/getHomeTaskList")
    fun getHomeList(@Query("page") page: String?): Call<ResponseBody>?


    /**
     * 我参与的活动
     * @return
     */
    @GET("/activity/getUserTaskList")
    fun getUserList(): Call<ResponseBody>?

    /**
     * Banner
     * @return
     */
    @GET("/banner")
    fun getBanner(): Call<ResponseBody>?

    /**
     * 日报列表
     * @param bodyMap
     * @return
     */
    @GET("/dailypaper/getDailyPaperList")
    fun getArticle(@QueryMap bodyMap: Map<String?, String?>?): Call<ResponseBody>?


    /**
     * 收款账户
     * @return
     */
    @GET("/payment/getWalletList")
    fun getWallet(): Call<ResponseBody>?


    /**
     * 我的账户安全
     * @return
     */
    @GET("/user/getUserSecurity")
    fun getUserSecurity(): Call<ResponseBody>?

    /**
     * 我的--绑定手机发送验证码
     * @return
     */
    @POST("/user/sendBindSms")
    fun getUserMobileCode(@Body bodyMap: Map<String?, String?>?): Call<ResponseBody>?


    /**
     * 我的--绑定手机
     * @return
     */
    @POST("/user/bindMobile")
    fun getUserMobileBind(@Body bodyMap: Map<String?, String?>?): Call<ResponseBody>?


    /**
     * 我的--更新头像
     * @return
     */
    @POST("/user/setAvatar")
    fun getUserAvatar(@Body bodyMap: Map<String?, String?>?): Call<ResponseBody>?

    /**
     * 我的--更该昵称
     * @return
     */
    @POST("/user/setNickname")
    fun getUserNickname(@Body bodyMap: Map<String?, String?>?): Call<ResponseBody>?

    /**
     * 绑定银行卡
     * @return
     */
    @POST("/payment/bank")
    fun getWalletBank(@Body bodyMap: Map<String?, String?>?): Call<ResponseBody>?


    /**
     * 绑定身份证
     * @return
     */
    @POST("/payment/setRealname")
    fun getRealname(@Body bodyMap: Map<String?, String?>?): Call<ResponseBody>?

}