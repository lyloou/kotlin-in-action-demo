package com.lyloou.chapter2

fun main(args: Array<String>) {
    println("hello, world")
    var a = 3
    if (a > 3) println('a') else println('b')
    println(if (a > 3) 3 else 4)
    println(max(1234))

    val answer: Int
    println("32")
    answer = if (a > 3) 323 else 5
    println(answer)
    println("aksdfj, ${a}b\$")
    println("adklsfjak, ${if (3 > 5) "aaabbb" else "asdfasdf"}")

    println(Rectangle(15, 16).isSquare)
    println(Rectangle(15, 16).square())
    println(createRandomRectangle())
}

fun max(a: Int): Int = if (a > 3) 3 else 5

