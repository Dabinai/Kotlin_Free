package com.junxin.freepaykotlin

import android.Manifest
import androidx.fragment.app.Fragment
import com.junxin.freepaykotlin.adapter.ViewPagerAdapter
import com.junxin.freepaykotlin.bean.LoginSuccessBean
import com.junxin.freepaykotlin.fragment.DiscoverFragment
import com.junxin.freepaykotlin.fragment.HomeFragment
import com.junxin.freepaykotlin.fragment.MineFragment
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity() {

    var mFragments = mutableListOf<Fragment>()
    override fun getLayout(): Int = R.layout.activity_main

    override fun onInit() {
        requestPermissions()
        EventBus.getDefault().register(this)
        initData()
    }

    private fun requestPermissions() {
        var permissions: Array<String> = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PRECISE_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA
        )

        RxPermissions(this).requestEach(permissions.toString())
            .subscribe {
                if (it.granted) {

                } else if (it.shouldShowRequestPermissionRationale) {

                } else {

                }
            }

    }

    fun initData() {
        mFragments.add(HomeFragment())
        mFragments.add(DiscoverFragment())
        mFragments.add(MineFragment())
        view_pager.isUserInputEnabled = false
        view_pager.adapter = ViewPagerAdapter(this, mFragments)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_bottom_home -> view_pager.currentItem = 0
                R.id.item_bottom_discover -> view_pager.currentItem = 1
                R.id.item_bottom_mine -> view_pager.currentItem = 2
                else->{
                    view_pager.currentItem = 0
                }
            }
            true
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun EbStatus(bean :LoginSuccessBean) {
        view_pager.currentItem = 2;
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun changeFragment(index: Int) {
        view_pager.currentItem = index
    }
}