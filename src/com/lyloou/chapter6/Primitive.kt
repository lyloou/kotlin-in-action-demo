package com.lyloou.chapter6

import org.junit.jupiter.api.Test


class Primitive {
    @Test
    fun testPrimitive() {
        val x = 1
        val list = listOf(1L, 2L, 3L)
//        println(list.contains(x)) // error occur when compiled
        println(list.contains(x.toLong()))

        println(100_000)

        val c: Char = 'A'
        println(c.javaClass)

        val b: Byte = 1
        val i: Int = b.toInt()
        println(i)

        val y = 1
        val y1: Int = y
//        val y2:Byte = y   // can not convert automatically
        val l1: Long = y + 3L // can large
//        val i1:Int = l1 + 2 // can not small
    }

    @Test
    fun testAny() {
        val answer: Any = 1
        println(answer.javaClass) // class java.lang.Integer

        val i: Int = 1
        println(i.javaClass)
    }
}