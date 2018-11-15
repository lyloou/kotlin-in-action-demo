package com.lyloou.chapter4.dataclass

data class DataClient(val name: String, val postalCode: Int)

fun main(args: Array<String>) {
    compareClient()
}

private fun compareClient() {
    val client1 = DataClient("lisi", 12345)
    val client2 = DataClient("lisi", 12345)
    println(client1.equals(client2))
    println(client1 == client2) // compare with objects
    println(client1 === client2) // compare with reference
}