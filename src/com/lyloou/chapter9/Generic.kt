package com.lyloou.chapter9

import org.junit.jupiter.api.Test

class Generic {
    @Test
    fun testAny() {
        val list: List<Any> = listOf(1, "a", 'c')
        println(list.javaClass)
    }

    @Test
    fun testSlice() {
        var charRange = 'a'..'z'
        val list = charRange.toList()
        val indices = 0..2
        println(charRange.javaClass)
        println(list.javaClass)
        println(indices.javaClass)
        println(list.slice(indices))
    }

    // p255
    val <T> List<T>.penultimate: T
        get() = this[size - 2]

    val String.asdf
        get() = "asdf:" + this
    var String.valAsdf: String
        get () = ":::" + this
        set(value) {
            println(":::" + value)
        }

    @Test
    fun testGet() {
        var s = "some string"
        println(s.asdf)

        println(s.valAsdf)
        s.valAsdf = "asdf"
        Double
    }

    fun <T : Number> List<T>.filterAndCount(): Int {
        return this.filter { (it.toInt() % 2) == 0 }.count()
    }

    @Test
    fun testNumber() {
        val list: List<Number> = listOf(1, 2, 3, 1.2, 1.3, 4.3f, 4L)
        println(list.filterAndCount())
    }


    fun addAnswer(list: MutableList<Any>) {
        list.add(42)
    }

    @Test
    fun testAddAnswer() {
//        val list = mutableListOf("abc", "bac") // as MutableList<String>
        val list: MutableList<Any> = mutableListOf("abc", "bac")

        addAnswer(list)
        // list.maxBy { it.length } // can not be compiled
    }

    fun genericNumber(number: List<Number>) {
        println(number.count())
    }

    @Test
    fun testGenericNumber() {
        val number: List<Int> = listOf(1, 2, 3, 4, 5)
        // The List interface in Kotlin represents a read-only collection. If A is a subtype of
        //B , then List<A> is a subtype of List<B> . Such classes or interfaces are called covariant p272
        genericNumber(number) // compare java version
        val strs: List<String> = listOf("1", "2")
//        genericNumber(strs)


    }

    open class Animal {
        fun feed() {
            println("feed..")
        }
    }

    class Herd<T : Animal> {
        val list = mutableListOf<T>();
        val size: Int get() = list.size
        operator fun get(i: Int): T {
            return list[i]
        }

        fun add(t: T) {
            list.add(t)
        }
    }

    // Because the Herd class has an API similar to List and doesn’t allow its clients to add
    // or change the animals in the herd, you can make it covariant and change the calling code
    // accordingly p274
    // NOTE: the out keyword is assign to class Herd (p275), here assign to feedAll function
    fun feedAll(animals: Herd<out Animal>) { // if no out keyword, feedAll(cats) can not be invoked
        for (i in 0 until animals.size) {
            animals[i].feed()
        }
    }

    class Cat : Animal() {
        fun cleanLitter() {
            println("clean litter")
        }
    }

    fun takeCareOfCats(cats: Herd<Cat>) {
        for (i in 0 until cats.size) {
            cats[i].cleanLitter()
            feedAll(cats)
        }
    }

    @Test
    fun testAnimal() {
        val cats = Herd<Cat>()
        cats.add(Cat())
        cats.add(Cat())
        takeCareOfCats(cats)
        println("-----------")
        feedAll(cats)

    }

    interface FieldValidator<in T> {
        fun validate(input: T):
                Boolean
    }

    object DefaultStringValidator : FieldValidator<String> {
        override fun validate(input: String) =
                input.isNotEmpty()
    }

    object DefaultIntValidator : FieldValidator<Int> {
        override fun validate(input: Int) = input >= 1
    }
}