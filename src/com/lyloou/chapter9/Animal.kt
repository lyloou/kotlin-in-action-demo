package com.lyloou.chapter9

import org.junit.jupiter.api.Test

class Animal {
    open class Animal {
        fun feed() {
            println("feed..")
        }
    }

    class Herd<out T : Animal> {
        val list = mutableListOf<Animal>();
        val size: Int get() = list.size
        operator fun get(i: Int): T {
            return list[i] as T
        }

        fun add(t: Animal) {
            list.add(t)
        }
    }

    // Because the Herd class has an API similar to List and doesn’t allow its clients to add
    // or change the animals in the herd, you can make it covariant and change the calling code
    // accordingly p274
    // NOTE: the out keyword is assign to class Herd (p275), here assign to feedAll function
    fun feedAll(animals: Herd<Animal>) { // if no out keyword, feedAll(cats) can not be invoked
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
        takeCareOfCats(cats)
        println("-----------")
        feedAll(cats)
    }
}