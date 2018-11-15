package com.lyloou.chapter2

import com.lyloou.chapter2.Color.*

enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);

    fun rgb(): Int {
        return (r * 255 + g) * 255 + b
    }
}

fun main(args: Array<String>) {
    println(Color.RED.rgb())
    println(getMnemonic(Color.RED))
    println(getWarmth(Color.BLUE))
    println(mix(YELLOW, BLUE))
    println(mixOptimized(RED, YELLOW))
}

fun getMnemonic(color: Color) = when (color) {
    RED -> "Richard"
    ORANGE -> "Of"
    YELLOW -> "York"
    GREEN -> "Gave"
    BLUE -> "Battle"
    INDIGO -> "In"
    VIOLET -> "Vain"
}

fun getWarmth(color: Color) = when (color) {
    RED,
    ORANGE,
    YELLOW -> "warm"
    GREEN -> "neutral"
    BLUE,
    INDIGO,
    VIOLET -> "cold"
}

fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    else -> throw Exception("Dirty color")
}

fun mixOptimized(c1: Color, c2: Color) = when {
    (c1 == RED && c2 == YELLOW) -> GREEN
    (c1 == RED && c2 == YELLOW) -> GREEN
    else -> throw Exception("Unknow")
}