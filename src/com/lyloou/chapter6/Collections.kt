package com.lyloou.chapter6

import org.junit.jupiter.api.Test

class Collections {
    fun addValidNumbers(list: List<Int?>) {
        val filterNotNullList = list.filterNotNull()
        println("valid number :${filterNotNullList.size}")
        println("invalid number :${list.size - filterNotNullList.size}")
    }

    @Test
    fun testValidNumbers() {
        addValidNumbers(listOf(null, 1, 123, null, 11, 12))
    }

    fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
        for (item in source) {
            target.add(item)
        }
    }

    @Test
    fun testCopyElements() {
        val source: Collection<Int> = arrayListOf(1, 2, 3, 4)
        val target: MutableCollection<Int> = arrayListOf(0)
        copyElements(source, target)
        println(source)
        println(target)

        // source.add(5) // error occured
        println(source.javaClass)
        println(target.javaClass)
    }

    @Test
    fun testStringBuilderList() {
        val strBuilder = StringBuilder("abc")
        val list: Collection<StringBuilder> = arrayListOf(strBuilder)
        println(list)

        list.stream().map { it.append("def") }
        println(list)

        val c: ArrayList<Int> // compare with kotlin.collections.ArrayList that from org.jetbrains.kotlin:kotlin-stdlib-common:1.2.71
    }
}