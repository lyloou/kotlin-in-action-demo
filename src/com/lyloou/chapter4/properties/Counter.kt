package com.lyloou.chapter4.properties

class LengthCounter {
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += 1
    }
}

fun main(args: Array<String>) {
    val counter = LengthCounter()
    println(counter.counter)
    counter.addWord("hello")
    println(counter.counter)
    // counter.counter= 32 // error!
}