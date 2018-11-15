package com.lyloou.chapter3.extension

class A(var c: String) {
    fun getDiffC(): String {
        return "---$c---"
    }

    fun getDiffD(): String {
        return "!!!!!$c!!!!!"
    }
}

//fun CharSequence.split(vararg delimiters: String) = "123"
fun A.getDiffD(): String = "====${this.c}===="

fun main(args: Array<String>) {
//    callWho()

    val ds: String = "Bob/aBob/name.txt";
//    parseWithStringStandardMethod(ds)

//    parseWithStringRegularExpression(ds)

    tripleQuote()
}

fun tripleQuote() {
    val s = """${'$'}99.9,
        |<html>
        |<body>
        |<div> $ asdaHello <div>
        |<body>
        |<html> """.trimMargin()
    println(s)
}

private fun callWho() {
    println(A("a").getDiffC())
    println(A("a").getDiffD())
    var s: CharSequence = "ad:sf"
    println("=========:" + s.split(":"))
}

fun parseWithStringRegularExpression(ds: String) {
    // val s1 = "a\.fileName" // error occured
    val s1 = """a\.fileName"""
    println(s1)
    println(s1.javaClass)

    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val result = regex.matchEntire(ds)
    if (result != null) {
        val (d1, d2, d3) = result.destructured
        println("$d1, $d2, $d3")
    }


}

private fun parseWithStringStandardMethod(ds: String) {
    val after = ds.substringAfterLast("/")
    val before = ds.substringBeforeLast("/")
    println(after)
    println(before)
}

fun returnSplit(str: CharSequence, delimiters: String): List<String> {
    return str.split(delimiters)
}


class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    fun validate(user: User,
                 value: String,
                 fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                    "Cannot save user ${user.id}: $fieldName is empty")
        }
    }
    validate(user, user.name, "Name")
    validate(user, user.address, "Address")
// Save user to the database
}