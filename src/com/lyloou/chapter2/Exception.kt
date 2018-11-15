package com.lyloou.chapter2

import java.io.BufferedReader
import java.io.StringReader

fun main(args: Array<String>) {
    val message = readNumber(BufferedReader(StringReader("not a number")))
    println(message)
}

fun readNumber(reader: BufferedReader): Int? {
    val number = try {
        val line = reader.readLine()
        Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        println(e.message)
        null
    } finally {
        reader.close()
    }
    println("==> $number")
    return number
}


