package com.lyloou.chapter3.extension

fun main(args: Array<String>) {
    // function extension
    val c = "the test text"
    println(c.lastChar())

    // properties extension
    println(c.lastChar)
    val builder = StringBuilder(c)
    println(builder.lastChar)
    builder.lastChar = '!'
    println(builder.lastChar)
    println(builder)
}

fun String.lastChar(): Char = this[this.length - 1]

val String.lastChar: Char
    get() = this[length - 1]
var StringBuilder.lastChar: Char
    get() = this[length - 1]
    set(value) = this.setCharAt(length - 1, value)
