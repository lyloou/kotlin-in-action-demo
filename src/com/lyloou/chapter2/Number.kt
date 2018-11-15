package com.lyloou.chapter2


interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr
class Mul(val left: Expr, val right: Expr) : Expr

fun main(args: Array<String>) {
    println(eval(Sum(Sum(Num(12), Num(13)), Num(12))))
    println(eval(Mul(Sum(Num(1), Num(2)), Num(4))))

    println()

    println(evalWhen(Sum(Sum(Num(12), Num(13)), Num(12))))
    println(evalWhen(Mul(Sum(Num(1), Num(2)), Num(4))))

    println()

    println(evalWithLog(Sum(Sum(Num(12), Num(13)), Num(12))))
    println(evalWithLog(Mul(Sum(Num(1), Num(2)), Num(4))))
}

fun eval(e: Expr): Int {
    if (e is Num) {
        return e.value
    }
    if (e is Sum) {
        return eval(e.left) + eval(e.right)
    }
    if (e is Mul) {
        return eval(e.left) * eval(e.right)
    }
    throw Exception("error")
}

fun evalWhen(e: Expr): Int = when {
    (e is Num) -> e.value
    (e is Sum) -> evalWhen(e.right) + evalWhen(e.left)
    (e is Mul) -> evalWhen(e.right) * evalWhen(e.left)
    else -> throw Exception("ad")
}

fun evalWhenConcise(e: Expr): Int = when (e) {
    is Num -> e.value
    is Sum -> evalWhenConcise(e.right) + evalWhenConcise(e.left)
    is Mul -> evalWhenConcise(e.right) * evalWhenConcise(e.left)
    else -> throw Exception("error")
}

fun evalWithLog(e: Expr): Int = when (e) {
    is Num -> {
        e.value
    }
    is Sum -> {
        var left = evalWithLog(e.left)
        var right = evalWithLog(e.right)
        println("$left + $right = ${left + right}")
        left + right
    }
    is Mul -> {
        var left = evalWithLog(e.left)
        var right = evalWithLog(e.right)
        println("$left * $right = ${left * right}")
        left * right
    }
    else -> throw Exception("error")
}
