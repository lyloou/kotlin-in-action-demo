package com.lyloou.chapter6

import org.junit.jupiter.api.Test

class Arrays {
    @Test
    fun testArray() {
        val letters = Array(26) { i -> ('a' + i).toString() }
        println(letters.joinToString(" "))

        val squares = IntArray(5) { i -> (i + 1) * (i + 1) }
        println(squares.joinToString(","))
        println(squares.filter { it > 3 })
        squares.forEachIndexed { index, i -> println("$index, $i") }
    }
}