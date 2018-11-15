package com.lyloou.chapter4.accessor

class User(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println("""
                Address was changed for $name:
                "$field" -> "$value".""".trimIndent())
            field = value
        }
}

fun main(args: Array<String>) {
    val user = User("LiSi")
    println(user.name)
    println(user.address)

    user.address = "address lisi"
    // user.name = "alsdkj"  // error
    println(user.name)
    println(user.address)
}