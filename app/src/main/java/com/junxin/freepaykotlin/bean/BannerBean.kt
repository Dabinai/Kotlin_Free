package com.junxin.freepaykotlin.bean

/*
 * ：Created by z on 2020-07-28
 */   class BannerBean {
    /**
     * code : 0
     * msg :
     * data : [{"img":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/banner_rank.png","type":"url","params":{"url":"https://docs.qq.com/doc/DZUpwVmdkTWpCZGV0","desc":"(无用字段。 打开URL，暂时不跳转)"}},{"img":"https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/banner_co.png","type":"mp","params":{"app_id":"0000000000","desc":"(无用字段。 打开小程序或者H5离线包，暂时不跳转)"}},{"img":"https://s3.cn-north-1.amazonaws.com.cn/s3.haitiand.cn/freego/banner.png","type":"app","params":{"desc":"(无用字段。 打开原生页面， 暂时不处理)"}}]
     */
    var code = 0
    var msg: String? = null
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * img : https://s3.cn-northwest-1.amazonaws.com.cn/s3.jianos.com/activity/banner_rank.png
         * type : url
         * params : {"url":"https://docs.qq.com/doc/DZUpwVmdkTWpCZGV0","desc":"(无用字段。 打开URL，暂时不跳转)"}
         */
        var img: String? = null
        var type: String? = null
        var params: ParamsBean? = null

        class ParamsBean {
            /**
             * url : https://docs.qq.com/doc/DZUpwVmdkTWpCZGV0
             * desc : (无用字段。 打开URL，暂时不跳转)
             */
            var url: String? = null
            var desc: String? = null

        }
    }
}