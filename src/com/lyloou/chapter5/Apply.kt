package com.lyloou.chapter5

import org.junit.jupiter.api.Test

class Apply {
    @Test
    fun testApply() {
        println(2.apply { println(this * this) }.toString())
        println(StringBuilder().apply { append("abcde").append("12345") }.toString())
    }

    @Test
    fun testBuildString() {
        val str = buildString {
            for (letter in 'A'..'Z') {
                append(letter)
            }
        }
        println(str.javaClass)
        println(str)
    }
}