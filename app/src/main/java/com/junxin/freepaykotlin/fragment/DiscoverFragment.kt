package com.junxin.freepaykotlin.fragment

import androidx.fragment.app.Fragment
import com.junxin.freepaykotlin.R
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment :BaseFragment(){

    var fragments = mutableListOf<Fragment>()

    override fun getLayout(): Int = R.layout.fragment_discover

    override fun init() {

        initTab()
        initFragments()
    }

    private fun  initTab(){
        var tabs = mutableListOf<String>()
        tabs.add("全部")
        tabs.add("即将开始")
        tabs.add("进行中")
        tabs.add("售罄")
        tabs.add("结束")

        daily_tab.setItems(tabs)
        daily_tab.setSlect(0)
        daily_tab.setSelectedListener {
            if(viewpager_day_son.currentItem != it){
                viewpager_day_son.currentItem = it
            }
        }
    }

    private fun initFragments(){
        for (index in 0..4){

        }
    }

}