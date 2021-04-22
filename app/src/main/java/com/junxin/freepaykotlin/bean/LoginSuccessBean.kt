package com.junxin.freepaykotlin.bean

class LoginSuccessBean {
    var avatar: String? = null
    var nickname: String? = null
    var platform: String? = null

    constructor() {}
    constructor(avatar: String?, nickname: String?) {
        this.avatar = avatar
        this.nickname = nickname
    }

    constructor(avatar: String?, nickname: String?, platform: String?) {
        this.avatar = avatar
        this.nickname = nickname
        this.platform = platform
    }

}