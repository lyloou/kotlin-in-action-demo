Reified type parameters allow you to refer at runtime to the specific types used as
type arguments in an inline function call. (For normal classes or functions, this isn’t
possible, because type arguments are erased at runtime.)
p252

Declaration-site variance lets you specify whether a generic type with a type
argument is a subtype or a supertype of another generic type with the same base type and
a different type of argument. For example, it regulates whether it’s possible to pass
arguments of type List<Int> to functions expecting List<Any> . Use-site variance
achieves the same goal for a specific use of a generic type and therefore accomplishes the
same task as Java’s wildcards.
p252

Because Kotlin has had generics from the beginning, it doesn’t support
raw types, and the type arguments must always be defined.
p253



```kotlin
fun <T : Number> List<T>.sum() : T
```

```kotlin
class Processor<T> {
	fun process(value: T) {
		value?.hashCode()
	}
}
```
In the process function, the parameter value is nullable, even though T isn’t marked
with a question mark. This is the case because specific instantiations of the Processor
class can use a nullable type for T
```kotlin
val nullableStringProcessor = Processor<String?>()
nullableStringProcessor.process(null)
```
p260

```kotlin
class Processor<T : Any>{ // guarantee a non-null type
    fun process(value: T){
        value.hashCode()
    }
}
```
p260

 you can normally be sure that a List<String>
contains only strings and a List<Int> contains only integers, because the compiler
knows the type arguments and ensures that only elements of the correct type are stored in
each list. (You can deceive the compiler through type casts or by using Java raw types to
access the list, but you need to make a special effort to do that.)
p262

This is true in general(generics are erased at runtime), but there’s one case where this limitation can be avoided:
inline functions. Type parameters of inline functions can be reified, which means you can
refer to actual type arguments at runtime.
p264

Making the function `inline` may
improve performance if this function uses lambdas as arguments: the lambda code may
be inlined as well, so no anonymous class will be created. This section shows another
case when inline functions are helpful: their type parameters can be reified.
`inline` functions are helpful : their type parameters can be reified
As a reminder, if you mark a
function with the inline keyword, the compiler will replace every call to the function
with the actual code implementing the function.
p264

avoid the case that generics erased at runtime.
If you declare the previous `isA` function as `inline` and mark the type parameter as
`reified` , you can check the value to see whether it’s an instance of T
reified type parameter
```kotlin
inline fun <reified T> isA(value: Any) = value is T // if no reified keyword, error will be occured
>>> println(isA<String>("abc"))
true
>>> println(isA<String>(123))
false
```
p264

Note that inline function with reified type parameters can’t be called from
Java code. Kotlin code doesn’t call them either—instead, they’re inlined directly
at the call site, so they’re compiled in a special way, not as regular methods.
Because Java doesn’t support inlining, such functions are inaccessible to it.
p266


Simplifying the startActivity function on Android
```kotlin
inline fun <reified T : Activity>
        Context.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}
startActivity<DetailActivity>()
```
p268


Making the function inline may
improve performance if this function uses lambdas as arguments: the lambda code may
be inlined as well, so no anonymous class will be created. This section shows another
case when inline functions are helpful: their type parameters can be reified.
- In type checks and casts ( is , !is , as , as? )
- To use the Kotlin reflection APIs, as we’ll discuss in chapter 10 ( ::class )
- To get the corresponding java.lang.Class ( ::class.java )
- As a type parameter to call other functions
You can’t do the following:
- Create new instances of the class specified as a type parameter
- Call methods on the companion object of the type parameter class
- Use a non-reified type parameter as a type argument when calling a function with a reified type parameter
- Mark type parameters of classes, properties, or non-inline functions as reified
p268

Nullable types provide an example of when subtype isn’t the same as subclass; see
figure 9.5. A non- null type is a subtype of its nullable version, but they both correspond
to one class. You can always store the value of a non- null type in a variable of a
nullable type, but not vice versa ( null isn’t an acceptable value for a variable of a non-
null type):
```kotlin
val s: String = "abc"
val t: String? = s
```



Note:
Use-site variance declarations in Kotlin correspond directly to Java
wildcards. MutableList<out T> in Kotlin means the same as
MutableList<? extends T> in Java. The in -projected MutableList<in T>
corresponds to Java’s MutableList<? super T> .
p283

Speaking about Java wildcards, MyType<*> in Kotlin
corresponds to Java’s MyType<?>
p284

