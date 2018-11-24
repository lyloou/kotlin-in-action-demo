package com.lyloou.chapter7

import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*
import java.util.stream.IntStream.range
import java.util.stream.IntStream.rangeClosed

class Pointer {
    data class Times(val x: Int)

    data class Point(val x: Int, val y: Int)

    operator fun Point.plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun Point.times(times: Times): Point {
        return Point(x * times.x, y * times.x)
    }

    @Test
    fun testPlus() {
        var p1 = Point(1, 2)
        val p2 = Point(3, 4)
        // p1 += p2
        println(p1 + p2)

        val times = Times(23);
        println(p1 * times)
        println((p1 + p2) * times)
        println(p1 + p2 * times) // use the same precedence as the standard numeric types
    }


    operator fun String.minus(str: String): String = this + "-" + str

    @Test
    fun testString() {
        println("asdf" - "asd")
    }

    @Test
    fun testBitwise() {
        println(4 shl 2)
        println(4 shr 2)
        println(4 ushr 2)

        println(-4 shr 2)
        println(-4 ushr 2)

        println(4 and 2)
        println(4 or 2)
        println(4 xor 2)
//        println(4 inv 2)
    }

    operator fun StringBuilder.plusAssign(str: String) {
        this.append("----")
                .append(str)
                .append("----")
    }

    @Test
    fun testStringBuilder() {
        val sb = StringBuilder("adsf")
        sb += "asdf"
        println(sb.toString())
    }

    operator fun Point.compareTo(p: Point): Int {
        return (x + y) - (p.x + p.y)
    }

    @Test
    fun testCompare() {
        val p1 = Point(1, 2)

        val p2 = Point(3, 4)
        println(p1 > p2)
        println(p1 < p2)
        println(p1 <= p2)

        // All Java classes that implement the Comparable interface can be compared in Kotlin
        // using the concise operator syntax:
        println("abcd" < "bcd")
    }

    @Test
    fun testMap() {
//        val tmp = mapOf(1 to 2, 2 to 4, 3 to 6)
        val tmp = mutableMapOf(1 to 2, 2 to 4, 3 to 6)
        println(tmp.javaClass)
        val value = tmp[2]
        println(value)
        tmp[2] = 6
    }

    @Test
    fun testList() {
        var list = ArrayList<Int>()
        list.apply {
            add(12)
            add(13)
            add(14)
        }

        println(list)

    }

    @Test
    fun testRange() {
        print("10 in 2 until 10: ${10 in 2 until 10}")
        print("\nuntil: ")
        for (i in 2 until 10) { // not include 10
            print("$i ")
        }

        print("\nrange: ")
        for (i in range(2, 10)) { // not include 10
            print("$i ")
        }

        print("\nrangeClosed: ")
        for (i in rangeClosed(2, 10)) { // include 10
            print("$i ")
        }

        print("\n..: ")
        for (i in 2..10) { // include 10
            print("$i ")
        }
    }

    @Test
    fun testRangeDate() {
        val now = LocalDate.now()
        val vocation = now..now.plusDays(10)
        println(now.plusWeeks(1) in vocation)
    }

    @Test
    fun testIterator() {
        for (a in "abc") {
            println(a)
        }
    }

    operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
            object : Iterator<LocalDate> {
                var current = start
                override fun hasNext(): Boolean = current <= endInclusive

                override fun next(): LocalDate = current.apply {
                    current = plusDays(1)
                }

            }

    @Test
    fun testClosedRangeLocalDate() {
        val newYear = LocalDate.ofYearDay(2018, 1)
        val daysOff = newYear.minusDays(1)..newYear
        for (i in daysOff) {
            println(i)
        }
    }

    @Test
    fun testApply() {
        var sb = StringBuilder()
        sb.apply { append("asdf") }
        println(sb.toString())


        var s = "asd"
        s.apply {
            s = this.toUpperCase()
        }
        println(s)
    }

    data class Person(val name: String, val age: Int)

    @Test
    fun testDestructuringDeclarations() {
        val (x, y) = Point(1, 2)
        println("$x, $y")

        var point = Point(3, 4)
        println(point.component1())
        println(point.component2())

        var person = Person("lou", 18)
        val (x1, y1) = person
        println("$x1, $y1")
        println("${person.component1()}, ${person.component2()}")

    }
}