package com.lyloou.chapter4.dataclass

import java.util.stream.IntStream.range

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

    fun copy(name: String = this.name, postalCode: Int = this.postalCode) =
            Client(name, postalCode)

    override fun toString(): String {
        return "Client(name='$name', postalCode=$postalCode)"
    }
}


fun main(args: Array<String>) {
//    compareClient()

//    containClient()
    copyClient()
}

fun copyClient() {
    val lisi = Client("lisi", 1234)
    println(lisi)
    val wangwu = lisi.copy(name = "wangwu")
    println(wangwu)

}

fun containClient() {
    val sets = setOf(Client("lisi", 12345))
    for (i in range(1, 100)) {
        println(sets.contains(Client("lisi", 12345))) // false if not override hashCode
    }
}

private fun compareClient() {
    val client1 = Client("lisi", 12345)
    val client2 = Client("lisi", 12345)
    println(client1.equals(client2))
    println(client1 == client2) // compare with objects
    println(client1 === client2) // compare with reference
}