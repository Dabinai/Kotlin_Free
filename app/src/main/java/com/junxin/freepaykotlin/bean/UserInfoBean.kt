package com.junxin.freepaykotlin.bean

/**
 * 保存个人信息的Bean
 */
class UserInfoBean {
    /**
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJqdW54aW4iLCJhdWQiOiJhcHAiLCJpYXQiOjE2MDYyOTUwNDgsImV4cCI6MTYwODg4NzA0OCwiZGF0YSI6eyJ1c2VyX2lkIjoyfX0.lcHc5IEGW9CsvnQ6lkupbPIY5aVnTyY_1Ya9g7cnilI
     * nickname : BXRKGMGKOLC-
     * avatar : null
     * jwtToken : "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJqdW54aW4iLCJhdWQiOiJhcHAiLCJpYXQiOjE2MTc4Njk5NDEsImV4cCI6MTYyMDQ2MTk0MSwiZGF0YSI6eyJ1c2VyX2lkIjoxLCJleHBpcmVfYXQiOjE2MTg0NzQ3NDF9fQ.msLPQhsx-RI3KvIdlNrxOSPQaARqDgUYR1OP1wFNTO8"
     */
    var token: String? = null
    var nickname: String? = null
    var avatar: String? = null
    var jwtToken: String? = null

    override fun toString(): String {
        return "UserInfoBean{" +
                "token='" + token + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                '}'
    }
}