package com.lyloou.chapter10

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.reflect.KAnnotatedElement
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.PROPERTY)
annotation class JsonExclude

@Target(AnnotationTarget.PROPERTY)
annotation class JsonName(val name: String)

interface Company {
    val name: String
}

data class CompanyImpl(override val name: String) : Company

data class Person(val name: String, @DeserializeInterface(CompanyImpl::class) val company: Company)
data class Person2(val name: String, @CustomSerializer(DateSerializer::class) val birthDate: Date)

class DateSerializer : ValueSerializer<Date> {
    override fun fromJsonValue(jsonValue: Any?): Date {
        return Date()
    }

    override fun toJsonValue(value: Date): Any? {
        return ""
    }

}

@Target(AnnotationTarget.PROPERTY)
annotation class DeserializeInterface(val targetClass: KClass<out Any>)

@Target(AnnotationTarget.PROPERTY)
annotation class CustomSerializer(val serializerClass: KClass<out ValueSerializer<*>>)

interface ValueSerializer<T> {
    fun toJsonValue(value: T): Any?
    fun fromJsonValue(jsonValue: Any?): T
}

fun KProperty<*>.getSerializer(): ValueSerializer<Any?>? {
    val customSerializerAnn = findAnnotation<CustomSerializer>() ?: return null
    val serializerClass = customSerializerAnn.serializerClass

    val valueSerializer = serializerClass.objectInstance ?: serializerClass.createInstance()
    @Suppress("UNCHECKED_CAST")
    return valueSerializer as ValueSerializer<Any?>
}

private fun StringBuilder.serializeObject(obj: Any) {
    // append(x)
    obj.javaClass.kotlin.memberProperties
            .filter { it.findAnnotation<JsonExclude>() == null }
            .joinToStringBuilder(this, prefix = "{", postfix = "}") {
                serializeProperty(it, obj)
            }

}

fun <T> Iterable<T>.joinToStringBuilder(stringBuilder: StringBuilder, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", callback: ((T) -> Unit)? = null): StringBuilder {
    return joinTo(stringBuilder, separator, prefix, postfix, limit, truncated, {
        if (callback == null)
            return@joinTo it.toString()

        callback(it)
        ""
    })
}

private fun StringBuilder.serializeProperty(prop: KProperty1<Any, *>, obj: Any) {
    val name = prop.findAnnotation<JsonName>()?.name ?: prop.name
    serializeString(name)
    append(": ")
    val value = prop.get(obj)
    val jsonValue = prop.getSerializer()?.toJsonValue(value) ?: value
    serializePropertyValue(jsonValue)
}

private fun StringBuilder.serializePropertyValue(value: Any?) {
    when (value) {
        null -> append("null")
        is String -> serializeString(value)
        is Number, is Boolean -> append(value.toString())
        is List<*> -> serializeList(value)
        else -> serializeObject(value)
    }
}

private fun StringBuilder.serializeList(data: List<Any?>) {
    data.joinToStringBuilder(this, prefix = "[", postfix = "]") {
        serializePropertyValue(it)
    }
}

private fun Char.escape(): Any =
        when (this) {
            '\\' -> "\\\\"
            '\"' -> "\\\""
            '\b' -> "\\b"
            '\u000C' -> "\\f"
            '\n' -> "\\n"
            '\r' -> "\\r"
            '\t' -> "\\t"
            else -> this
        }

private fun StringBuilder.serializeString(s: String) {
    append('\"')
    s.forEach { append(it.escape()) }
    append('\"')
}

fun serialize(obj: Any): String = buildString { serializeObject(obj) }

// inline fun <reified T> String.asdf(ast: T): String? = "::$ast"

inline fun <reified T> KAnnotatedElement.findAnnotation(): T? = annotations.filterIsInstance<T>().firstOrNull()


data class Person3(@JsonName("alias") val firstName: String, /*@JsonExclude*/ val age: Int)

class Jkid {
    @Test
    fun testJsonName() {
        val person3 = Person3("Bob", 25)
        val kClass = person3.javaClass.kotlin
        val props = kClass.memberProperties.filter { it.findAnnotation<JsonExclude>() == null }
        for (prop in props) {
            val jsonNameAnn = prop.findAnnotation<JsonName>()
            val propertyName = jsonNameAnn?.name ?: prop.name
            println("-->${jsonNameAnn?.name}")
            println(propertyName)
            println("__________")
            println(prop.get(person3))
        }

        println(serialize(person3))
    }
}