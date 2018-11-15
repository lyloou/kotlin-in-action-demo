package com.lyloou.chapter4.dataclass

class Client(val name: String, val postalCode: Int) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client) {
            return false
        }
        return other.name == name && other.postalCode == postalCode
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + postalCode
        return result
    }
}

fun main(args: Array<String>) {
    val client1 = Client("lisi", 12345)
    val client2 = Client("lisi", 12345)
    println(client1.equals(client2))
    println(client1 == client2) // compare with objects
    println(client1 === client2) // compare with reference
}