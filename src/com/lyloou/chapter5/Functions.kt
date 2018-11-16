package com.lyloou.chapter5

import org.junit.jupiter.api.Test
import java.io.File


class Functions {


    @Test
    fun generateSequenceWithFile() {
        fun File.isInsideHiddenDirectory() = generateSequence(this) { it.parentFile }.find { it.isHidden }
        val file = File("/User/lyloou/.hidden/text.txt") // TODO !!! not work well when no hidden directory
        println(file.isInsideHiddenDirectory())
    }

    @Test
    fun generateSequence() {
        val natureNumber = generateSequence(0) { it + 1 }
        val numbersTo100 = natureNumber.takeWhile { it <= 100 }
        println(numbersTo100.javaClass)
        println(numbersTo100.sum())
    }

    @Test
    fun exampleLazyAndEager() {
        val numbers = listOf(1, 2, 3, 4)
        numbers.map {
            println("map$it"); it * it
        }.find {
            println("find$it");it > 3
        }

        numbers.asSequence().map {
            println("map$it"); it * it
        }.find {
            println("find$it");it > 3
        }
    }

    @Test
    fun exampleLazyAndEager2() {
        val numbers = listOf(1, 2, 3, 4)
        println(numbers.map {
            println("map$it"); it * it
        }.filter {
            println("filter$it");it > 3
        }.toSet())

        println(numbers.asSequence().map {
            println("map$it"); it * it
        }.filter {
            println("filter$it");it > 3
        }.toSet())
    }

    @Test
    fun exampleBook() {
        class Book(val title: String, val authors: List<String>)

        val book1 = Book("Kotlin in action", listOf("Dmitry Jemerov", "Svetlana Isakova"))
        val book2 = Book("Kotlin in action2", listOf("Dmitry Jemerov", "Svetlana Isakova", "Bob", "Jim"))
        val books = listOf(book1, book2)
        println(books.flatMap { it.authors }.toSet())

        println(books.asSequence()
                .filter { it.title.startsWith("Kotlin") }
                .map { it.title }
                .toSet())
        println(books.filter { it.title.startsWith("Kotlin") }
                .flatMap { it.authors }
                .map { it.toUpperCase() }
                .asSequence()
                .toSet())

    }

    @Test
    fun examplePerson() {
        data class Person(val name: String, val age: Int)

        val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Jim", 48))
        println(people.filter { it.age > 30 })
        println(people.map { it.name })
        println(people.map(Person::name))

        val maxAge = people.maxBy(Person::age)?.age
        println(people.filter { it.age == maxAge })

        println("all: " + people.all { it.age < 30 })
        println("any: " + people.any { it.age < 30 })
        println("filter: " + people.filter { it.age < 30 }.size)
        println("count: " + people.count { it.age < 30 }) // more efficient than above size
        println("find: " + people.find { it.age < 35 })

        val people2 = listOf(Person("Alice", 29), Person("Bob", 31), Person("Jim", 31))
        val groups = people2.groupBy { it.age }
        println(groups)
        println(groups.mapKeys { it.key })
        println(groups.mapValues { it.key })
    }

    @Test
    fun exampleNumber() {
        val numbers = listOf(1, 2, 3, 4)
        println(numbers.filter { it > 2 })
        println(numbers.filter { it % 2 == 0 })
        println(numbers.map { it * it })
    }

}