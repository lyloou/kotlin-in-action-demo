package com.lyloou.chapter3.override

open class View {
    open fun click() = println("view clicked")
}

open class Button : View() {
    override fun click() = println("button clicked")
}

fun main(args: Array<String>) {
    val view = View()
    view.click()
    view.showOff()

    println("=========")

    val btn = Button()
    btn.click()
    btn.showOff()

    println("=========")
    val btn2: View = Button() // dynamic
    btn2.click() // normal for dynamic call, Button.click will be called
    btn2.showOff() // extensions function not match dynamic call, View.showOff will be called

}

fun View.showOff() {
    this.click()
    println("click is from View")
}

fun Button.showOff() {
    this.click()
    println("click is from Button")
}

fun Button.showOff(i: Int) {
    this.click()
    println("click is from Button, $i")
}