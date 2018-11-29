package com.lyloou.chapter10

import org.junit.jupiter.api.Test
import kotlin.reflect.KFunction
import kotlin.reflect.full.memberProperties

class Annotations {
    @Deprecated(message = "Use test2() instead", replaceWith = ReplaceWith("test2()"))
    fun test1() {
        println("test1")
    }

    fun test2() {
        println("test2")
    }

    @Test
    fun testDeprecated() {
        test1()
    }


    @Test
    fun hasTempFolder() {
    }

    fun testListStringSuppress(list: List<*>) {
        @Suppress("UNCHECKED_CAST")
        val strings = list as List<String>
    }

    class Person(val name: String, val age: Int)

    @Test
    fun testPerson() {
        val person = Person("Bob", 25)
        println(person.javaClass)
        println(person.javaClass.simpleName)
        println(person.javaClass.kotlin)
        println(person.javaClass.kotlin.simpleName)
        println(person.javaClass.kotlin.qualifiedName)

        person.javaClass.kotlin.memberProperties.forEach { println(it.name) }
    }

    fun sum(x: Int, y: Int) = x + y
    fun foo(x: Int) = println(x)
    var counter = 0
    @Test
    fun testFoo() {
        val fun1 = ::foo
        fun1.call(1)
        println(fun1.javaClass.typeName)
        val kFunction2: KFunction<Int> = ::sum
        println(kFunction2.call(1, 2))

        val property0 = ::counter
        println(property0.get())
        println(property0.setter.call(21))
        println(property0.get())
    }

    fun <T> jsb(callback: ((T) -> Unit)? = null): ((T) -> CharSequence)? { // test Jkid.StringBuilder.joinToStringBuilder
        return label@{
            if (callback == null)
                return@label it.toString()
            callback(it)
            "1234"
        }
    }

    @Test
    fun testJsb() {
        val jsb1 = jsb<String>()
        println(jsb1?.invoke("asdf"))
        println("--------")
        val jsb2 = jsb<String>(callback = {
            println("++$it++")
        })
        println(jsb2?.invoke("asdf"))

    }

}