package com.junxin.freepaykotlin.bean

/**
 * 我的活动和官方活动 总和
 */
class TaskCommonBean {
    /**
     * task_id : 1
     * start_time : 2020-11-09 20:39:31
     * active_time : 2020-12-11 21:23:08
     * title : U2C智能台灯(无线充版)
     * end_time : 2020-12-29 20:40:04
     * sub_title : 智能台灯,"智慧"生活
     * price : 289
     * cover : https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/act4.png
     * app_id : 10000001
     * entries : 0
     */
    /**
     * mpAppId : 1
     * title : [今日益行旅游卡]连续打卡25天全额返 
     * subTitle : 旅游一卡通 5000多景点免费畅游
     * price : 99
     * cover : https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/aa.png
     * jumpUrl : null
     * ApiUrl : null
     */
    var mpAppId: String? = null
    var title: String? = null
    var subTitle: String? = null
    var price: String? = null
    var cover: String? = null
    var jumpUrl: String? = null
    var apiUrl: String? = null

    /**
     * 首页中我的任务
     */
    var userTaskBeanList: List<TaskCommonBean>? = null

    /**
     * 首页中  ：用来判断是我的活动还是官方活动
     * 0 = 官方活动
     * 1 = 官方活动的标题
     * 2 = 我的活动
     * 3 = 任务为空时
     *
     * 4 = 我的活动标题
     *
     *
     *
     */
    var type = 0

}