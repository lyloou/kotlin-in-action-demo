package com.lyloou.chapter2

data class Person(val name: String, val age: Int? = null)

fun main(args: Array<String>) {
    val persons = listOf(Person("Alice"), Person("Bob", age = 29))
    val oldest = persons.maxBy<Person, Int>(selector = { (_, age): Person -> age ?: 0 })
    println("The oldest is $oldest")

    val s = "asdf"

    if (s is String) {
        println(s.toUpperCase())
    }
}




