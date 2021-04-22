package com.junxin.freepaykotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junxin.freepaykotlin.R
import com.junxin.freepaykotlin.bean.TaskCommonBean
import com.junxin.freepaykotlin.view.RoundImageView
import com.junxin.freepaykotlin.widget.SingleClickListener

/**
 * 我的界面适配器
 */
class MineAdapter(
    private val context: Context,
    private var items: List<TaskCommonBean>
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    /**
     * 2 = 我的活动
     * 3 = 任务为空时
     * 4 = 我的活动标题
     */
    private val TASK_TYPE = 2
    private val EMPTY_TYPE = 3
    private val TITLE_TYPE = 4
    private val inflater: LayoutInflater
    private var taskRoot: View? = null
    private var taskTitleRoot: View? = null
    private var emptyRoot: View? = null
    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        val type = items[position].type
        return if (type == 4) {
            TITLE_TYPE
        } else if (type == 2) {
            TASK_TYPE
        } else {
            EMPTY_TYPE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == TITLE_TYPE) {
            taskTitleRoot = inflater.inflate(R.layout.mine_item_task_title, parent, false)
            TextTaskTitleHolder(taskTitleRoot!!)
        } else if (viewType == TASK_TYPE) {
            taskRoot = inflater.inflate(R.layout.mine_item_task, parent, false)
            TaskHolder(taskRoot!!)
        } else {
            emptyRoot = inflater.inflate(R.layout.mine_item_empty, parent, false)
            EmptyHolder(emptyRoot!!)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TextTaskTitleHolder) {
            holder.bindView(
                position
            )
        } else if (holder is TaskHolder) {
            holder.bindView(position)
        } else {
            (holder as EmptyHolder).bindView(position)
        }
    }

    fun setData(items: List<TaskCommonBean>) {
        this.items = items
        notifyDataSetChanged()
    }

    private inner class TaskHolder(root: View) : RecyclerView.ViewHolder(root) {
        fun bindView(position: Int) {
            val ll = taskRoot!!.findViewById<LinearLayout>(R.id.item_home_LL)
            val dataBean = items[position]
            val imageView: RoundImageView = taskRoot!!.findViewById(R.id.home_rv_item_img)
            val imageUrl = dataBean.cover
            Glide.with(context).load(imageUrl).into(imageView)
            val main_rv_item_title =
                taskRoot!!.findViewById<TextView>(R.id.home_rv_item_title)
            val main_rv_item_des = taskRoot!!.findViewById<TextView>(R.id.home_rv_item_des)
            val main_rv_item_bottom_price =
                taskRoot!!.findViewById<TextView>(R.id.home_rv_item_bottom_price)
            val btnPunch =
                taskRoot!!.findViewById<Button>(R.id.btn_punch)
            main_rv_item_title.text = dataBean.title
            main_rv_item_des.text = dataBean.subTitle
            main_rv_item_bottom_price.text = "原价 " + dataBean.price + "元"
            val clickListener: SingleClickListener =
                object : SingleClickListener() {
                    override fun onSingleClick(v: View?) {
                        if (listener != null) listener!!.onClick(position, dataBean.mpAppId)
                    }
                }
            btnPunch.setOnClickListener(clickListener)
            taskRoot!!.setOnClickListener(clickListener)
        }
    }

    private inner class TextTaskTitleHolder(root: View) :
        RecyclerView.ViewHolder(root) {
        fun bindView(position: Int) {}
    }

    private inner class EmptyHolder(root: View) :
        RecyclerView.ViewHolder(root) {
        fun bindView(position: Int) {}
    }

    private var listener: ItemClick? = null
    fun setOnItemClick(listener: ItemClick?) {
        this.listener = listener
    }

    interface ItemClick {
        fun onClick(position: Int, appId: String?)
    }

    private val TAG = "zhu"

    init {
        inflater = LayoutInflater.from(context)
    }
}