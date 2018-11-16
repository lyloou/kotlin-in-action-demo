package com.lyloou.chapter5.withjava

import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch

class Postpone {
    @Test
    fun postpone() {
        val latch = CountDownLatch(2)

        Computation().postponeComputation(1) { println("hello");latch.countDown() }

        val anonymousObject: Runnable = object : Runnable {
            override fun run() {
                println("world")
                latch.countDown()
            }
        }
        Computation().postponeComputation(1, anonymousObject)

        latch.await()

    }
}

