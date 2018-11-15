package com.lyloou.chapter2

import java.util.*

fun main(args: Array<String>) {
//    testFizzBuzz()
//    testIterateChar()
//    testIterateList()

    testIn()
}

fun testIn() {
    println(isNotDigit('1'))
    println(isLetter('.'))

    println(isNotDigit('a'))
    println(isLetter('z'))

    recognise('a')
    recognise('1')
    recognise('.')
}

fun isNotDigit(c: Char) = c !in '0'..'9'

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun recognise(c: Char) = when (c) {
    in '0'..'9' -> println("$c is digit")
    in 'a'..'z' -> println("$c is letter")
    else -> println("I don't know about $c")
}


fun testIterateList() {
    val list = arrayListOf(1, 23, 2, 24, 23, 83, 21, 25)
    for ((index, item) in list.withIndex()) {
        println("$index, $item")
    }
}

private fun testFizzBuzz() {
    for (i in 1..100) {
        println(fizzBuzz(i))
    }

    for (i in 100 downTo 1 step 2) {
        println(fizzBuzz(i))
    }

    for (i in 0 until 10) {
        println(i)
    }
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "fizzBuzz"
    i % 3 == 0 -> "fizz"
    i % 5 == 0 -> "buzz"
    else -> "$i"
}


fun testIterateChar() {
    val binaryReps = TreeMap<Char, String>() // will be sorted
    for (c in 'Z' downTo 'A') {
        binaryReps[c] = Integer.toBinaryString(c.toInt())
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }
}