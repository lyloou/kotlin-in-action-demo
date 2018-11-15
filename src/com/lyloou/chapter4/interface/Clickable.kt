package com.lyloou.chapter4.`interface`

interface Clickable {
    fun click()
    fun showOff() = println("default showOff from Clickable")
}

interface Focusable {
    fun focus()
    fun showOff() = println("default showOff from Focusable")
}

class Button : Clickable, Focusable {
    override fun focus() {
        println("button focused")
    }

    override fun click() = println("button clicked")
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
        println("override showOff from Button")
    }
}

fun main(args: Array<String>) {
    Button().click()
    Button().focus()
    Button().showOff()
}