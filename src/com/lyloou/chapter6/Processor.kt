package com.lyloou.chapter6

import org.junit.jupiter.api.Test

class Processor {
    interface Processor<T> {
        fun process(): T
    }

    class NoResultProcessor : Processor<Unit> {
        override fun process() {// do stuff
            println("process from kotlin")
            return Unit // can omit
        }
    }

    @Test
    fun test() {
        NoResultProcessor().process()
        ProcessorJavaTest().process()
    }
}
