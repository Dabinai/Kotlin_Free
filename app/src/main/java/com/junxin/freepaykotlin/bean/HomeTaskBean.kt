package com.junxin.freepaykotlin.bean

import java.io.Serializable

/**
 * 首页任务Bean
 */
class HomeTaskBean : Serializable {
    /**
     * code : 0
     * msg :
     * data : {"total":13,"currentPage":1,"lastPage":1,"data":[{"mpAppId":"1","title":"[今日益行旅游卡]连续打卡25天全额返 ","subTitle":"旅游一卡通 5000多景点免费畅游","price":"99","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/aa.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"今日益行旅游一卡通0元购打卡返现","subTitle":"旅游一卡通 5000多景点免费畅游","price":"99","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/aa.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"Kindle青春版+旅游年卡【打卡返全额】","subTitle":"全国14条线路，旅游途中，阅读同在","price":"289","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/act_org.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"U2C智能台灯(无线充版)","subTitle":"智能台灯,\"智慧\"生活","price":"11","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/act4.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"小墨早教机器人","subTitle":"智能机器,\"智慧\"学习","price":"499","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/robot-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"全国景区年票一卡通-VIP金卡","subTitle":"一卡在手 全国景区免费游","price":"298","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/vipcard-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"摩雷Morel汽车音响 柯蕾kinetic 602HE","subTitle":"高音低音喇叭 升级改装套装","price":"2280","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/yinxiang-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"墨馨智能台灯","subTitle":"墨馨智能台灯,创造智慧生活","price":"298","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/molamp-cover.jpg","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"核桃彩蛋年货","subTitle":"开心0元购神秘彩蛋 过年一起玩","price":"24.9","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/hetao-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"S400香薰加湿器","subTitle":"居家必备 滋润你的小屋","price":"159","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/jiashiqi-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"正负离子空气净化器","subTitle":"性价比净化器 还你清新好空气","price":"129","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/jinghuaqi-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"S700暖风机","subTitle":"更舒适的\u201c风\u201d度选择","price":"199","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/huanfengji-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"小墨早教机器人","subTitle":"AI早教 陪伴你的孩子","price":"499","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/robot2-cover.png","jumpUrl":null,"ApiUrl":null}]}
     */
    var code = 0
    var msg: String? = null
    var data: DataBeanX? = null

    class DataBeanX : Serializable {
        /**
         * total : 13
         * currentPage : 1
         * lastPage : 1
         * data : [{"mpAppId":"1","title":"[今日益行旅游卡]连续打卡25天全额返 ","subTitle":"旅游一卡通 5000多景点免费畅游","price":"99","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/aa.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"今日益行旅游一卡通0元购打卡返现","subTitle":"旅游一卡通 5000多景点免费畅游","price":"99","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/aa.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"Kindle青春版+旅游年卡【打卡返全额】","subTitle":"全国14条线路，旅游途中，阅读同在","price":"289","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/act_org.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"U2C智能台灯(无线充版)","subTitle":"智能台灯,\"智慧\"生活","price":"11","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/act4.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"小墨早教机器人","subTitle":"智能机器,\"智慧\"学习","price":"499","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/robot-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"全国景区年票一卡通-VIP金卡","subTitle":"一卡在手 全国景区免费游","price":"298","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/vipcard-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"摩雷Morel汽车音响 柯蕾kinetic 602HE","subTitle":"高音低音喇叭 升级改装套装","price":"2280","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/yinxiang-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"墨馨智能台灯","subTitle":"墨馨智能台灯,创造智慧生活","price":"298","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/molamp-cover.jpg","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"核桃彩蛋年货","subTitle":"开心0元购神秘彩蛋 过年一起玩","price":"24.9","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/hetao-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"S400香薰加湿器","subTitle":"居家必备 滋润你的小屋","price":"159","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/jiashiqi-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"正负离子空气净化器","subTitle":"性价比净化器 还你清新好空气","price":"129","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/jinghuaqi-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"S700暖风机","subTitle":"更舒适的\u201c风\u201d度选择","price":"199","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/huanfengji-cover.png","jumpUrl":null,"ApiUrl":null},{"mpAppId":null,"title":"小墨早教机器人","subTitle":"AI早教 陪伴你的孩子","price":"499","cover":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/robot2-cover.png","jumpUrl":null,"ApiUrl":null}]
         */
        var total = 0
        var currentPage = 0
        var lastPage = 0
        var data: List<TaskCommonBean>? = null

    } //    /**
    //     * code : 0
    //     * msg :
    //     * data : [{"app_id":"04916A58CAC4DE6C7113EE10347A5BCE","start_time":"2021-01-27 11:49:12","end_time":"2021-01-27 11:49:26","create_time":"2021-01-27 11:49:20","entries":0,"title":"墨馨智能台灯(无线充版)","paas_id":"10000101","sub_title":"智能台灯,\"智慧\"生活","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/act4.png","price":"297"},{"app_id":"12E379BD013C4A42D1CB3665F4F8EE09","start_time":"2021-01-27 20:21:14","end_time":"2022-01-27 20:21:17","create_time":"2021-01-27 20:21:35","entries":0,"title":"U2C智能台灯(无线充版)","paas_id":"10000102","sub_title":"智能台灯,\"智慧\"生活","cover":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/act4.png","price":"297"}]
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