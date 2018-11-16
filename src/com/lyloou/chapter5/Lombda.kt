package com.lyloou.chapter5

fun main(args: Array<String>) {
    // save lambda function to variable
    val func = { x: Int, y: Int -> x + y }
    println(func(8, 23));


    // call lambda function directly
    { println("hello") }()

    run { println(func(8, 21)) }
}

