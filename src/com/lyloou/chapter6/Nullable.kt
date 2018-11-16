package com.lyloou.chapter6

import org.junit.jupiter.api.Test

class Nullable {
    fun strLen(s: String): Int = s.length
    fun strLenSafe(s: String?): Int = if (s == null) 0 else s.length
    @Test
    fun strTest() {
        println(strLen("asdf"))
//        println(strLen(null)) // error
        println(strLenSafe(null))
    }
}