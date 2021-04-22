package com.junxin.freepaykotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junxin.freepaykotlin.R
import com.junxin.freepaykotlin.bean.TaskCommonBean
import com.junxin.freepaykotlin.view.RoundImageView
import com.junxin.freepaykotlin.widget.SingleClickListener

class HomeAdapter(private var context: Context, private var items: List<TaskCommonBean>,private var isHaveUserTask: Boolean) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var MINE_TYPE = 2
    var TASK_TITLE_TYPE = 1
    var TASK_TYPE = 3
    var LAST_TYPE = 0

    var mineTaskRoot: View? = null
    var taskRoot: View? = null
    var taskTitleRoot: View? = null
    private val inflater :LayoutInflater


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MINE_TYPE) {
            mineTaskRoot = inflater.inflate(R.layout.home_item_mine_task, parent, false)
            MineTaskHolder(mineTaskRoot!!)
        } else if (viewType == TASK_TITLE_TYPE) {
                taskTitleRoot = inflater.inflate(R.layout.home_item_task_title,parent,false)
            TextTaskTitleHolder(taskTitleRoot!!)
        }else{
            taskRoot = inflater.inflate(R.layout.home_item_task,parent,false)
            TaskHolder(taskRoot!!)
        }

    }

    override fun getItemCount(): Int {
        return   items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MineTaskHolder){
            holder.bindView(position)
        }else if(holder is TextTaskTitleHolder){
            holder.bindView(position)
        }else{
            (holder as TaskHolder).bindView(position)
        }
    }

    fun setData(items:List<TaskCommonBean>, isHaveUserTask: Boolean){
        this.items= items
        this.isHaveUserTask = isHaveUserTask
        notifyDataSetChanged()

    }

    private inner class TextTaskTitleHolder(root: View) : RecyclerView.ViewHolder(root) {
        fun bindView(position: Int) {
            if(isHaveUserTask){
                taskTitleRoot!!.background = context.getDrawable(R.color.app_color_f3)
            }else{
                taskTitleRoot!!.background = null
            }
        }
    }

    private inner class MineTaskHolder(root: View) : RecyclerView.ViewHolder(root) {
        fun bindView(position: Int) {
            val ctl : ConstraintLayout = mineTaskRoot!!.findViewById(R.id.home_item_mine_ctl)
            val ctlTwo : ConstraintLayout = mineTaskRoot!!.findViewById(R.id.home_item_mine_ctl_two)
            val allTitle  = mineTaskRoot!!.findViewById<TextView>(R.id.home_item_mine_all_title)
            val iv = mineTaskRoot!!.findViewById<ImageView>(R.id.home_item_mine_iv)
            val tv = mineTaskRoot!!.findViewById<TextView>(R.id.home_item_mine_tv)
            val tvSub = mineTaskRoot!!.findViewById<TextView>(R.id.home_item_mine_tv_sub)
            val btnOne = mineTaskRoot!!.findViewById<Button>(R.id.home_item_mine_btn)
            val ivTwo = mineTaskRoot!!.findViewById<ImageView>(R.id.home_item_mine_iv_two)
            val tvTwo = mineTaskRoot!!.findViewById<TextView>(R.id.home_item_mine_tv_two)
            val tvSubTwo = mineTaskRoot!!.findViewById<TextView>(R.id.home_item_mine_tv_sub_two)
            val btnTwo = mineTaskRoot!!.findViewById<Button>(R.id.home_item_mine_btn_two)
            val userTaskBeanList = items[position].userTaskBeanList
            if (userTaskBeanList!!.size == 1) {
                ctlTwo.visibility = View.GONE
                ctl.visibility = View.VISIBLE
                Glide.with(context).load(userTaskBeanList[0].cover).into(iv)
                tv.text = userTaskBeanList[0].title
                tvSub.text = userTaskBeanList[0].subTitle
            } else {
                ctl.visibility = View.VISIBLE
                ctlTwo.visibility = View.VISIBLE
                Glide.with(context).load(userTaskBeanList[0].cover).into(iv)
                tv.text = userTaskBeanList[0].title
                tvSub.text = userTaskBeanList[0].subTitle
                Glide.with(context).load(userTaskBeanList[1].cover).into(ivTwo)
                tvTwo.text = userTaskBeanList[1].title
                tvSubTwo.text = userTaskBeanList[1].subTitle
            }
            val clickListenerOne: SingleClickListener = object : SingleClickListener() {
                override fun onSingleClick(v: View?) {
                    if (listener != null) listener!!.onClick(position, userTaskBeanList[0].mpAppId)
                }
            }
            ctl.setOnClickListener(clickListenerOne)
            btnOne.setOnClickListener(clickListenerOne)
            val clickListenerTwo: SingleClickListener = object : SingleClickListener() {
                override fun onSingleClick(v: View?) {
                    if (listener != null) listener!!.onClick(position, userTaskBeanList[1].mpAppId)
                }
            }
            ctlTwo.setOnClickListener(clickListenerTwo)
            btnTwo.setOnClickListener(clickListenerTwo)
            val clickListenerThree: SingleClickListener = object : SingleClickListener() {
                override fun onSingleClick(v: View?) {
                    if (listener != null) listener!!.onSkip()
                }
            }
            allTitle.setOnClickListener(clickListenerThree)
        }
    }

    private inner class TaskHolder(root: View) : RecyclerView.ViewHolder(root) {
        fun bindView(position: Int) {
            val ll = taskRoot!!.findViewById<LinearLayout>(R.id.item_home_LL)
            val dataBean = items[position]
            val imageView :RoundImageView = taskRoot!!.findViewById(R.id.home_rv_item_img)
            val imageUrl = dataBean.cover
            Glide.with(context).load(imageUrl).into(imageView)
            val main_rv_item_title = taskRoot!!.findViewById<TextView>(R.id.home_rv_item_title)
            val main_rv_item_des =  taskRoot!!.findViewById<TextView>(R.id.home_rv_item_des)
            val main_rv_item_bottom_price = taskRoot!!.findViewById<TextView>(R.id.home_rv_item_bottom_price)
            val btnPush = taskRoot!!.findViewById<Button>(R.id.btn_punch)
            main_rv_item_title.text = dataBean.title
            main_rv_item_des.text = dataBean.subTitle
            main_rv_item_bottom_price.text= dataBean.price+"元"
            val clickListener : SingleClickListener = object : SingleClickListener(){
                override fun onSingleClick(v: View?) {
                    if(listener != null){
                        listener!!.onClick(position,dataBean.mpAppId)
                    }
                }
            }

            btnPush.setOnClickListener(clickListener)
            taskRoot!!.setOnClickListener(clickListener)
        }
    }

    private var listener : ItemClick? =null

    fun setOnItemClick(listener:ItemClick){
        this.listener = listener
    }
    interface ItemClick{
        fun onClick(position: Int,appId:String?)
        fun onSkip()
    }
    init {
        inflater = LayoutInflater.from(context)
    }



}