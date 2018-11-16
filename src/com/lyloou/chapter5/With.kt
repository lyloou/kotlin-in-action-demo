package com.lyloou.chapter5

import org.junit.jupiter.api.Test
import java.util.stream.IntStream.range

class With {
    @Test
    fun testWith() {
        val stringBuilder = StringBuilder()
        with(stringBuilder) {
            for (a in range(1, 10)) {
                append(a)
            }
        }
        println(stringBuilder.toString())
    }

    @Test
    fun testWith2() {
        fun alphabet() = with(StringBuilder()) {
            for (letter in 'A'..'Z') {
                append(letter)
            }
            append("\nNow I know the alphabet!")
            println("toString:" + this@With.toString())
            println("toString:" + this.toString())
            this.toString() //  The result is the last expression in the lambda.
        }
        println(alphabet())
    }
}