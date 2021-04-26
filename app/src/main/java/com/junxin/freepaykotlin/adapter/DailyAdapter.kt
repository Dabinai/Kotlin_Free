package com.junxin.freepaykotlin.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junxin.freepaykotlin.R
import com.junxin.freepaykotlin.bean.DisDataBean
import com.junxin.freepaykotlin.widget.SingleClickListener

class DailyAdapter(private var context: Context, private var items: MutableList<DisDataBean>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var root: View? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        root = LayoutInflater.from(context).inflate(R.layout.item_daily,parent,false)
        return DailyViewHolder(root!!)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DailyViewHolder).bindView(position)
    }


    fun addMore(newItems :MutableList<DisDataBean>?){
        this.items.addAll(newItems!!)
        notifyDataSetChanged()
    }

    private inner class DailyViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        fun bindView(position: Int) {
            val tit = root!!.findViewById<TextView>(R.id.daily_item_title)
            tit.text = items[position].title
            val price = root!!.findViewById<TextView>(R.id.daily_item_price)
            price.text = items[position].price
            val img = root!!.findViewById<ImageView>(R.id.daily_item_icon)
            Glide.with(context).load(items[position].thumbCover)
                .placeholder(R.mipmap.daily_item_icon).into(img)
            val hour_price = root!!.findViewById<TextView>(R.id.daily_item_line2_hour_price)
            val priceStr = "<font color='#F54A3F'>" + items[position].salary + "</font>元"
            hour_price.text = Html.fromHtml(priceStr)
            val dif_score = root!!.findViewById<TextView>(R.id.daily_item_line2_difficulty_content)
            dif_score.text = items[position].diffScore
            val startTime = root!!.findViewById<TextView>(R.id.daily_item_line2_time_tit)
            startTime.text = items[position].startTime + "|"
            val stat = root!!.findViewById<TextView>(R.id.daily_item_line2_now)
            var str = ""
            var colo = context.resources.getColor(R.color.app_color_999)
            when (items[position].status) {
                0 -> str = "即将开始"
                1 -> {
                    str = "进行中"
                    colo = context.resources.getColor(R.color.app_color_049)
                }
                2 -> str = "已售磐"
                3 -> str = "已结束"
                else -> str = "开始了"
            }
            stat.setTextColor(colo)
            stat.text = str

            root!!.setOnClickListener(object : SingleClickListener() {
                override fun onSingleClick(v: View?) {
                    if (itemClick != null) itemClick!!.onClick(position)
                }
            })
        }
    }

    private var itemClick : ItemClick? =null

    fun  setOnItemClick(itemClick: ItemClick){
        this.itemClick = itemClick
    }

    interface ItemClick{
        fun onClick(position: Int)
    }
}