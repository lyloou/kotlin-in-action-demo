package com.lyloou.chapter4.constructor

class User constructor(_nickname: String) {
    val nickname: String

    init {
        nickname = _nickname
    }

}