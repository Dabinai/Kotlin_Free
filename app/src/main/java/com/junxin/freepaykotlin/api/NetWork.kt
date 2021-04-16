package com.junxin.freepaykotlin.api

import android.text.TextUtils
import com.google.gson.Gson
import com.junxin.freepaykotlin.api.callback.ApiErr
import com.junxin.freepaykotlin.api.callback.ApiSuccess
import com.junxin.freepaykotlin.iface.SPKeys
import com.junxin.freepaykotlin.utils.Lig
import com.junxin.freepaykotlin.utils.SPUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetWork : ApiUrl {

    companion object {
        private var instance: NetWork? = null
        fun getInstance(): NetWork? {
            if (instance == null) {
                instance = NetWork()
                instance!!.init(ApiUrl.BASE_URL)

            }
            return instance
        }
    }

    fun generateClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Lig.i("Network 请求体 = $it")
            }).setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(Interceptor { chain ->
                var token = ""
                try {
                    var user = SPUtil.getParam(SPKeys.USER, "").toString()
                    if (!TextUtils.isEmpty(user)) {
                        val jsonObject = JSONObject(user)
                        token = jsonObject.getString("jwtToken")
                    }
                } catch (e: Exception) {
                }
                val original = chain.request()
                val requestBuilder = original.newBuilder().addHeader("Connection", "keep-alive")
                    .addHeader("Authorization", "Bearer $token")
                    .addHeader("from", "android")
                    .method(original.method(), original.body()).build()
                chain.proceed(requestBuilder)
            }).connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).build()
    }


    private fun generateRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(generateClient()).build()
    }


    private var server: Server? = null

    fun init(baseUrl: String): NetWork? {
        server = generateRetrofit(baseUrl).create(Server::class.java)
        return instance
    }

    fun enqueueMethod(call: Call<ResponseBody>?, success: ApiSuccess?, error: ApiErr) {
        call?.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    var json = response.body()!!.string()
                    if (success != null) success.onResponse(call, json)
                } catch (e: Exception) {
                    Lig.e("NetWork.java 网络错误 : " + e)
                    if (error != null) error.onFailure(call, e)
                }
            }

        })
    }


    /* - - - - - - - - - - - - - - - - - - - - 下面是请求实例函数 - - - - - - - - - - - - - - - - - - - - - - */

    //=======================================================
    /*
     *  获取短信验证码
     */
    fun getMobileCode(
        bodyMap: Map<String?, String?>?,
        sucess: ApiSuccess?,
        err: ApiErr?
    ) {
        enqueueMethod(server!!.getMoboileCode(bodyMap)!!, sucess, err!!)
    }


    /*
     *  短信验证码登陆
     */
//    fun getMoboileCodeLogin(
//        bodyMap: Map<String?, String?>?,
//        sucess: ApiSuccess?,
//        err: ApiErr?
//    ) {
//        enqueueMethod(server.getMoboileCodeLogin(bodyMap), sucess, err!!)
//    }

    /*
     *  短信微信验证码
     */
    fun getWxMoboileCode(
        bodyMap: Map<String?, String?>?,
        sucess: ApiSuccess?,
        err: ApiErr?
    ) {
        enqueueMethod(server!!.getWxMoboileCode(bodyMap), sucess, err!!)
    }


    /*
     *  微信登录
     */
    fun sendWxLogin(
        bodyMap: Map<String?, String?>?,
        sucess: ApiSuccess?,
        err: ApiErr?
    ) {
        enqueueMethod(server!!.getWxLogin(bodyMap), sucess, err!!)
    }

    /*
     *  短信验证码与微信登陆
     */
//    fun getMoboileCodeAndWxLogin(
//        bodyMap: Map<String?, String?>?,
//        sucess: ApiSuccess?,
//        err: ApiErr?
//    ) {
//        enqueueMethod(server.getMoboileCodeAndWxLogin(bodyMap), sucess, err!!)
//    }


    /*
     *  账号密码登陆
     */
    fun getUserLogin(
        bodyMap: Map<String?, String?>?,
        sucess: ApiSuccess?,
        err: ApiErr?
    ) {
        enqueueMethod(server!!.getUserLogin(bodyMap), sucess, err!!)
    }

    /*
     *  官方活动
     */
    fun getHomeList(page: String?, sucess: ApiSuccess?, err: ApiErr?) {
        enqueueMethod(server!!.getHomeList(page), sucess, err!!)
    }

    /*
     *  我的活动
     */
    fun getUserList(sucess: ApiSuccess?, err: ApiErr?) {
        enqueueMethod(server!!.getUserList(), sucess, err!!)
    }

    /*
     *  Banner
     */
    fun getBanner(sucess: ApiSuccess?, err: ApiErr?) {
        enqueueMethod(server!!.getBanner(), sucess, err!!)
    }


    /*
     *  日报条目列表
     * */
    fun getArticle(
        bodyMap: Map<String?, String?>?,
        sucess: ApiSuccess?,
        err: ApiErr?
    ) {
        enqueueMethod(server!!.getArticle(bodyMap), sucess, err!!)
    }

    /*
     *  收款账户列表
     * */
    fun getWallet(sucess: ApiSuccess?, err: ApiErr?) {
        enqueueMethod(server!!.getWallet(), sucess, err!!)
    }

    /**
     * 我的账户安全
     */
    fun getUserSecurity(sucess: ApiSuccess?, err: ApiErr?) {
        enqueueMethod(server!!.getUserSecurity(), sucess, err!!)
    }


    /**
     * 我的--绑定手机发送验证码
     */
    fun getUserMobileCode(
        bodyMap: Map<String?, String?>?,
        sucess: ApiSuccess?,
        err: ApiErr?
    ) {
        enqueueMethod(server!!.getUserMobileCode(bodyMap), sucess, err!!)
    }

    /**
     * 我的--绑定手机
     */
    fun getUserMobileBind(
        bodyMap: Map<String?, String?>?,
        sucess: ApiSuccess?,
        err: ApiErr?
    ) {
        enqueueMethod(server!!.getUserMobileBind(bodyMap), sucess, err!!)
    }

    /**
     * 我的--更新头像
     */
    fun getUserAvatar(
        bodyMap: Map<String?, String?>?,
        sucess: ApiSuccess?,
        err: ApiErr?
    ) {
        enqueueMethod(server!!.getUserAvatar(bodyMap), sucess, err!!)
    }

    /**
     * 我的--更改昵称
     */
    fun getUserNickname(
        bodyMap: Map<String?, String?>?,
        sucess: ApiSuccess?,
        err: ApiErr?
    ) {
        enqueueMethod(server!!.getUserNickname(bodyMap), sucess, err!!)
    }

    /**
     * 绑定银行卡
     */
    fun getWalletBank(
        bodyMap: Map<String?, String?>?,
        sucess: ApiSuccess?,
        err: ApiErr?
    ) {
        enqueueMethod(server!!.getWalletBank(bodyMap), sucess, err!!)
    }

    /**
     * 绑定身份证
     */
    fun getRealname(
        bodyMap: Map<String?, String?>?,
        sucess: ApiSuccess?,
        err: ApiErr?
    ) {
        enqueueMethod(server!!.getRealname(bodyMap), sucess, err!!)
    }
}