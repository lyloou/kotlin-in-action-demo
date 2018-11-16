package com.lyloou.chapter6

import org.junit.jupiter.api.Test

class Nullable {
    fun strLen(s: String): Int = s.length
    fun strLenSafe(s: String?): Int = if (s == null) 0 else s.length
    @Test
    fun strTest() {
        println(strLen("asdf"))
//        println(strLen(null)) // error
        println(strLenSafe(null))

        val s: String? = ""
        println(s?.javaClass)
    }

    @Test
    fun testCaps() {
        fun printAllCaps(s: String?) {
            val allCaps: String? = s?.toUpperCase()
            println(allCaps)
        }
        printAllCaps("abc")
        printAllCaps(null)
    }

    @Test
    fun testAddress() {
        class Address(val streetAddress: String, val zipCode: Int,
                      val city: String, val country: String)

        class Company(val name: String, val address: Address?)
        class Person(val name: String, val company: Company?)

        fun Person.countryName(): String {
            val country = this.company?.address?.country
            return if (country != null) country else "Unknown"
        }

        val person = Person("Dmitry", Company("RR", Address("a", 1, "b", "country")))
        println(person.countryName())

        val person2 = Person("Bob", null)
        println(person2.countryName())
    }
}