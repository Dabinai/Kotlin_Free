package com.junxin.freepaykotlin.bean
/*
 * ：Created by z on 2020/10/9
 */ /**
 * 日报Bean
 */
class DailyBean {
    /**
     * code : 0
     * msg :
     * data : {"total":2,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":1,"title":"iReader 掌阅 FaceNote F1 电子书阅读 智能手机智能手机智能手机智能手机智能手机智能手机","price":"1980元包邮","salary":"9.99","status":1,"thumb_cover":"https://console.amazonaws.cn/s3.hellocode.cn/article/thumb.png","is_top":0,"hot_comments":["逗比阿红：\"放坑指南，谈谈如何获得掌阅的福利","逗比阿绿：\"哦吼，哦吼...\""],"start_time":"2020-09-29 11:52:28","dif_score":"5.1","people":2},{"id":2,"title":"京东阅读器 电子书阅读 智能手机智能手机智能手机智能手机智能手机智能手机","price":"xxx元限时购买","salary":"8.89","status":2,"thumb_cover":"https://console.amazonaws.cn/s3.hellocode.cn/article/thumb.png","is_top":0,"hot_comments":null,"start_time":"2020-09-29 11:52:28","dif_score":"5.0","people":0}]}
     */
    var code = 0
    var msg: String? = null
    var data: DataBeanX? = null

    class DataBeanX {
        /**
         * total : 2
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"id":1,"title":"iReader 掌阅 FaceNote F1 电子书阅读 智能手机智能手机智能手机智能手机智能手机智能手机","price":"1980元包邮","salary":"9.99","status":1,"thumb_cover":"https://console.amazonaws.cn/s3.hellocode.cn/article/thumb.png","is_top":0,"hot_comments":["逗比阿红：\"放坑指南，谈谈如何获得掌阅的福利","逗比阿绿：\"哦吼，哦吼...\""],"start_time":"2020-09-29 11:52:28","dif_score":"5.1","people":2},{"id":2,"title":"京东阅读器 电子书阅读 智能手机智能手机智能手机智能手机智能手机智能手机","price":"xxx元限时购买","salary":"8.89","status":2,"thumb_cover":"https://console.amazonaws.cn/s3.hellocode.cn/article/thumb.png","is_top":0,"hot_comments":null,"start_time":"2020-09-29 11:52:28","dif_score":"5.0","people":0}]
         */
        var total = 0
        var per_page = 0
        var current_page = 0
        var last_page = 0
        var data: MutableList<DisDataBean>? =
            null


    }
}