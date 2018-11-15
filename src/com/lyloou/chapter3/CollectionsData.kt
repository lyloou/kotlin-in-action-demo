package com.lyloou.chapter3

import com.lyloou.chapter3.extension.ExtensionFunction
import com.lyloou.chapter3.join.joinToString
import com.lyloou.chapter3.join.joinToStringWithDefault

val set = setOf(1, 7, 35)
val list = listOf(1, 7, 35)
val map = mapOf(1 to "one", 7 to "seven", 8 to "eight")
fun main(args: Array<String>) {

//    printThem()
//    printTheirJavaClass()
//    printWithJoinToString()
//    printWithJoinToStringDefault()
//    printStandardJoinToString()
//    testPair()

//    callJavaMethod()

    println("12.345-6.A".split("\\.".toRegex()))
    println("12.345-6.A".split("."))
}

private fun callJavaMethod() {
    ExtensionFunction.main(null)
}

private fun testPair() {
    val a = "a"
    val to = a to "asd"
    println(to.javaClass)
    spreadOperation()
}

private fun spreadOperation() {
    val arr = Array(3, init = { 0 })
    arr[0] = 0
    arr[1] = 1
    arr[2] = 2
    println(listOf(*arr))
}

fun printStandardJoinToString() {
    println(list.joinToString(prefix = "{", postfix = "}", separator = ":"))
    println(list.joinToString(" "))
    println(list.joinToString(prefix = "{", postfix = "}", separator = ":") { i -> "--->$i" })
}

fun printWithJoinToStringDefault() {
    println(joinToStringWithDefault(list))
    println(joinToStringWithDefault(set))
    println(joinToStringWithDefault(list, pre = "{", post = "}"))
}

private fun printWithJoinToString() {
    println(joinToString(set, ", ", "(", ")"))
    println(joinToString(set, sep = ", ", pre = "(", post = ")"))

    println(joinToString(list, ", ", "{", "}"))
    println(joinToString(list, sep = ", ", pre = "{", post = "}"))
}

private fun printTheirJavaClass() {
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)
}

private fun printThem() {
    println(set)
    println(list)
    println(map)
}

