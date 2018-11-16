package com.lyloou.chapter5

data class Person(val name: String, val age: Int)

fun main(args: Array<String>) {
    val people = listOf(Person("lisi", 18), Person("wangwu", 15))
    println(people.maxBy { it.age })
    println(people.maxBy(Person::age))
    println(people.maxBy(Person::name))

    println(people.joinToString(separator = ", ") { p: Person -> p.name })
}