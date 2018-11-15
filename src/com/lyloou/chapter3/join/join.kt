@file: JvmName("StringFunctions")

package com.lyloou.chapter3.join


fun <T> joinToString(items: Collection<T>, sep: String, pre: String, post: String): String {
    val builder = StringBuilder(pre)
    for ((index, item) in items.withIndex()) {
        if (index > 0) {
            builder.append(sep)
        }
        builder.append(item)
    }
    builder.append(post)
    return builder.toString()
}

fun <T> joinToStringWithDefault(items: Collection<T>, sep: String = ", ", pre: String = "(", post: String = ")"): String {
    val builder = StringBuilder(pre)
    for ((index, item) in items.withIndex()) {
        if (index > 0) {
            builder.append(sep)
        }
        builder.append(item)
    }
    builder.append(post)
    return builder.toString()
}