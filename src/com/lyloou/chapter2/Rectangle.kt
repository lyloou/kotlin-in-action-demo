package com.lyloou.chapter2

import java.util.*

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }

    fun square(): Boolean {
        return height == width;
    }

    override fun toString(): String {
        return "height:$height, width:$width, isSquare:$isSquare"
    }
}

fun createRandomRectangle(): Rectangle {
    val random = Random();
    return Rectangle(random.nextInt(100), random.nextInt(100))
}