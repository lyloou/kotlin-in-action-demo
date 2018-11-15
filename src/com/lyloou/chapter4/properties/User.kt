package com.lyloou.chapter4.properties

interface User {
    val nickname: String
}

class LiSi : User {
    override val nickname: String
        get() = "Li Si"
}

class WangWu(override val nickname: String) : User {

}

class ZhangSan : User {
    override val nickname = "ZhangSan"
}

fun main(args: Array<String>) {
    println(LiSi().nickname)
    println(WangWu("Wang Wu").nickname)
    println(ZhangSan().nickname)
}