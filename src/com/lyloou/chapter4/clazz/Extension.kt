package com.lyloou.chapter4.clazz

import com.lyloou.chapter4.`interface`.Focusable

open class Extension1 {
    fun hello() = println("hello1");
}

open class Extension2 {
    fun world() = println("world2");
}

class SubExtension : Extension1() {
    private val extension2: Extension2 = Extension2()
    fun print() {
        hello()
        extension2.world()
    }
}

fun main(args: Array<String>) {
    val subExtension = SubExtension()
    subExtension.print()
}


internal open class TalkativeButton : Focusable {
    override fun focus() {

    }

    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

internal fun TalkativeButton.giveSpeech() {
//    yell()
//    whisper()
}

class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}