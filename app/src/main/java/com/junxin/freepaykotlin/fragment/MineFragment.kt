package com.junxin.freepaykotlin.fragment

import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import com.junxin.freepaykotlin.MainActivity
import com.junxin.freepaykotlin.R
import com.junxin.freepaykotlin.adapter.MineAdapter
import com.junxin.freepaykotlin.api.NetWork
import com.junxin.freepaykotlin.api.callback.ApiErr
import com.junxin.freepaykotlin.api.callback.ApiSuccess
import com.junxin.freepaykotlin.bean.LoginSuccessBean
import com.junxin.freepaykotlin.bean.MineTaskBean
import com.junxin.freepaykotlin.bean.TaskCommonBean
import com.junxin.freepaykotlin.bean.UserInfoBean
import com.junxin.freepaykotlin.iface.SPKeys
import com.junxin.freepaykotlin.utils.GlideTool
import com.junxin.freepaykotlin.utils.SPUtil
import com.junxin.freepaykotlin.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_mine.*
import okhttp3.ResponseBody
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import kotlin.math.abs

class MineFragment : BaseFragment(), View.OnClickListener {

    var isLogin = false

    override fun getLayout(): Int = R.layout.fragment_mine

    override fun init() {
        EventBus.getDefault().register(this)
        initBar()

        tv_mine_collection.setOnClickListener(this)
        tv_mine_safety.setOnClickListener(this)
        iv_mine_user_head.setOnClickListener(this)
        tv_mine_login_name.setOnClickListener(this)
        ic_mine_setting.setOnClickListener(this)


        var userInfo = SPUtil.getParam(SPKeys.USER, "").toString()
        isLogin = !TextUtils.isEmpty(userInfo)

        if (isLogin) {
            var userInfoBean = Gson().fromJson(userInfo, UserInfoBean::class.java)
            activity?.let {
                GlideTool.instance?.setAvatar(it, iv_mine_user_head, userInfoBean.avatar)
            }
            tv_mine_login_name.text = userInfoBean.nickname
        }
        initUserTask()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun EbStatus(bean: LoginSuccessBean) {
        var userInfo = SPUtil.getParam(SPKeys.USER, "").toString()
        if(!TextUtils.isEmpty(userInfo)){
            var userInfoBean = Gson().fromJson(userInfo,UserInfoBean::class.java)
            activity?.let {
                GlideTool.instance?.setAvatar(it,iv_mine_user_head,userInfoBean.avatar)
            }
            tv_mine_login_name.text = userInfoBean.nickname
            isLogin = true
        }else{
            tv_mine_login_name.text = "立即登录"
            iv_mine_user_head.setImageResource(R.mipmap.head_un_login)
            isLogin = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
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
                        state = CollapsingToolbarLayoutState.EXPANDED //修改状态标记为展开
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

    private fun initUserTask() {
        NetWork.getInstance()?.getUserList(object : ApiSuccess {
            override fun onResponse(call: Call<ResponseBody>?, json: String?) {
                var mineTaskBean = Gson().fromJson(json, MineTaskBean::class.java)
                if (mineTaskBean.code != 0) {
                    ToastUtil.showShortToastCenter(mineTaskBean.msg)
                    return
                }
                mine_rc.layoutManager = LinearLayoutManager(activity)
                var data = mineTaskBean.data?.data
                if (data?.size == 0) {
                    var emptyTaskBean = TaskCommonBean()
                    emptyTaskBean.type = 3
                    data.add(emptyTaskBean)
                } else {
                    data?.forEach {
                        it.type = 2
                    }
                    var titleTaskBean = TaskCommonBean()
                    titleTaskBean.type = 4
                    data?.add(0, titleTaskBean)
                }

                var mineAdapter = MineAdapter(activity!!, data!!)
                mineAdapter.setOnItemClick(object : MineAdapter.ItemClick {
                    override fun onClick(position: Int, appId: String?) {


                    }

                })

            }

        }, object : ApiErr {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable) {
                ToastUtil.showShortToastCenter(t.toString())
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_mine_user_head, R.id.tv_mine_login_name -> {
                if (!isLogin) {
                    startActivity(Intent(activity, MainActivity::class.java))
                    return
                }

            }
            R.id.tv_mine_collection -> {

            }
            R.id.tv_mine_safety -> {

            }
            R.id.ic_mine_setting -> {

            }
        }
    }

}