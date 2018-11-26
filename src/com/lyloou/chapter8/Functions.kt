package com.lyloou.chapter8

import org.junit.jupiter.api.Test
import sun.misc.Lock
import java.io.BufferedReader
import java.io.FileReader

class Functions {
    val sum = { println(21 + 12) }
    val mul = { x: Int, y: Int -> x * y }
    @Test
    fun testSum() {
        sum()
        println(mul(3, 4))

        println(performRequest(mul))
        println(performRequest({ x: Int, y: Int -> x + y }))
        println(performRequest({ x: Int, y: Int -> x * y }))
        println(performRequest { x: Int, y: Int -> x / y })
        println(performRequest { x: Int, y: Int -> x - y })
        println(performRequest { x, y -> x * y })
    }

    private fun performRequest(callback: (x: Int, y: Int) -> Int): Int {
        return callback(1, 2)
    }

    private fun String.filter(predicate: (Char) -> Boolean): String {
        val sb = StringBuilder()
        for (i in 0 until this.length) {
            val c = this[i]
            if (predicate(c)) {
                sb.append(c)
            } else {
                sb.append('*')
            }
        }
        return sb.toString()
    }

    @Test
    fun testStringFilter() {
        println("asdf1123411abcd".filter { it in 'a'..'z' })
    }

    private fun getNullableValue(str: String?): String? {
        return str
    }

    @Test
    fun testNullable() {
        println(getNullableValue(null)?.toUpperCase() ?: "you are null")
        println(getNullableValue("abcd")?.toUpperCase() ?: "you are null")
    }

    @Test
    fun testList() {
        listOf(1, 2, 4).filter { it > 2 }.map { }
    }

    inline fun <T> synchronized(lock: Lock, action: () -> T): T {
        lock.lock()
        try {
            return action()
        } finally {
            lock.unlock()
        }
    }

    fun <T> Lock.withLock(action: () -> T): T {
        this.lock()
        try {
            return action()
        } finally {
            this.unlock()
        }
    }

    // p246
    @Test
    fun testLock() {
        val l = Lock()
        synchronized(l) {
            println("--> 1")
            println("--> 2")
        }

        l.withLock { println("--> 3") }
        l.withLock { println("--> 4") }
    }

    @Test
    fun testUse() {
        println(BufferedReader(FileReader("Readme.md")).use { br -> br.readLine() })
    }

    @Test
    fun testLabel1() {
        val list = listOf(0, 3, 2, 1, 10, 11)
        list.forEach {
            println("$it")
            if (it == 2) {
                return
            }
        }
        println("after")
    }

    @Test
    fun testLabel2() {
        val list = listOf(0, 3, 2, 1, 10, 11)

        list.forEach label@{
            println("$it")
            if (it == 2) {
                println("--------")
                return@label
            }
        }
        println("after")
    }

}