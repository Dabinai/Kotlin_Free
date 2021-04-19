package com.junxin.freepaykotlin.bean

import java.io.Serializable

/**
 * 我的任务Bean
 */
class MineTaskBean : Serializable {
    /**
     * code : 0
     * msg :
     * data : {"total":2,"currentPage":1,"lastPage":1,"data":[{"mpAppId":null,"title":"小墨早教机器人","subTitle":"智能机器,\"智慧\"学习","price":"499","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/robot-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":"1","title":"[今日益行旅游卡]连续打卡25天全额返 ","subTitle":"旅游一卡通 5000多景点免费畅游","price":"99","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/aa.png","jumpUrl":null,"ApiUrl":null}]}
     */
    var code = 0
    var msg: String? = null
    var data: DataBeanX? = null

    inner class DataBeanX : Serializable {
        /**
         * total : 2
         * currentPage : 1
         * lastPage : 1
         * data : [{"mpAppId":null,"title":"小墨早教机器人","subTitle":"智能机器,\"智慧\"学习","price":"499","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/robot-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":"1","title":"[今日益行旅游卡]连续打卡25天全额返 ","subTitle":"旅游一卡通 5000多景点免费畅游","price":"99","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/aa.png","jumpUrl":null,"ApiUrl":null}]
         */
        var total = 0
        var currentPage = 0
        var lastPage = 0
        var data: List<TaskCommonBean>? = null

    } //    /**
    //     * code : 0
    //     * msg :
    //     * data : [{"task_id":1,"start_time":"2020-11-09 20:39:31","active_time":"2020-12-11 21:23:08","title":"U2C智能台灯(无线充版)","end_time":"2020-12-29 20:40:04","sub_title":"智能台灯,\"智慧\"生活","price":"289","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/act4.png","app_id":"10000001","entries":0}]
    //     */
    //
    //    private int code;
    //    private String msg;
    //    private List<TaskCommonBean> data;
    //
    //    public int getCode() {
    //        return code;
    //    }
    //
    //    public void setCode(int code) {
    //        this.code = code;
    //    }
    //
    //    public String getMsg() {
    //        return msg;
    //    }
    //
    //    public void setMsg(String msg) {
    //        this.msg = msg;
    //    }
    //
    //    public List<TaskCommonBean> getData() {
    //        return data;
    //    }
    //
    //    public void setData(List<TaskCommonBean> data) {
    //        this.data = data;
    //    }
}