package com.lyloou.chapter11

import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Period

class Dsl {
    @Test
    fun testNumber() {
//        val yesterday = 1.day.ago
//        println(yesterday)
    }

    private fun buildString(builderAction: StringBuilder.() -> Unit): String {

        val sb = StringBuilder()
        sb.builderAction()

        return sb.toString()
    }

    // or you can do this way
    private fun buildString2(builderAction: StringBuilder.() -> Unit): String = StringBuilder().apply(builderAction).toString()

    @Test
    fun testBuildString() {

        val s = buildString {
            append("asdf1")
            append("asdf2")
            append("asdf3")
        }
        println(s)
    }

    open class Tag(val name: String) {
        private val children = mutableListOf<Tag>()
        protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
            child.init()
            children.add(child)
        }

        override fun toString() =
                "<$name>${children.joinToString("")}</$name>"
    }

    fun table(init: TABLE.() -> Unit) = TABLE().apply(init)
    class TABLE : Tag("table") {
        fun tr(init: TR.() -> Unit) = doInit(TR(), init)
    }

    class TR : Tag("tr") {
        fun td(init: TD.() -> Unit) = doInit(TD(), init)
    }

    class TD : Tag("td")


    fun createTable() = table {
        for (i in 1..2) {
            tr {
                td {
                }
            }
        }
    }


    @Test
    fun testTable() {
        println(createTable())
    }


    class Greeter(val greeting: String) {
        operator fun invoke(name: String) {
            println("$greeting, $name!")
        }
    }

    @Test
    fun testGreeter() {
        val greeter = Greeter("Hello")
        greeter("world")

        // or
        Greeter("Hello")("world")
    }


    infix fun <T> T.should(match: Matcher<T>) = match.test(this)
    interface Matcher<T> {
        fun test(value: T)
    }

    class startWith(val prefix: String) : Matcher<String> {
        override fun test(value: String) {
            if (!value.startsWith(prefix)) {
                throw AssertionError("String $value does not start with $prefix")
            }
        }
    }


    class beNotNegative() : Matcher<Int> {
        override fun test(value: Int) {
            if (value < 0) {
                throw AssertionError("Int $value is negative")
            }
        }
    }

    object start

    infix fun String.should(x: start): StartWrapper = StartWrapper(this)
    class StartWrapper(val value: String) {
        infix fun with(prefix: String) {
            if (!value.startsWith(prefix))
                throw AssertionError("String $value does not start with $prefix")
        }

    }

    object end

    infix fun String.should(x: end): EndWrapper = EndWrapper(this)
    class EndWrapper(val value: String) {
        infix fun with(prefix: String) {
            if (!value.endsWith(prefix))
                throw AssertionError("String $value does not end with $prefix")
        }

    }


    val Int.day: Period get() = Period.ofDays(this)
    val Period.ago: LocalDate get() = LocalDate.now() - this
    val Period.fromNow: LocalDate get() = LocalDate.now() + this

    @Test
    fun testStartWith() {
        "asdf".should<String>(startWith("as"))

        // or
        "asdf" should startWith("as")

        (10).should<Int>(beNotNegative())

        10 should beNotNegative()

        "asdf" should start with "as"
        "asdf" should end with "df"

        println(1.day.ago)
        println(1.day.fromNow)
    }

}