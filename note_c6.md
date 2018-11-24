The Elvis operator is often used together with the safe-call operator to substitute a
value other than null when the object on which the method is called is null . Here’s how
you can use this pattern to simplify the strLenSafe() example:
```kotlin
// before
fun strLenSafe(s: String?): Int = if(s!= null) s.length else 0
// after
fun strLenSafe(s: String?): Int = s?.length ?: 0
```


```kotlin
fun String?.isNullOrBlank(): Boolean = // Extension of a nullable String
this == null || this.isBlank() // The second "this" is a smart cast.
```
When you declare an extension function for a nullable type (ending with ? ), that
means you can call this function on nullable values; and this in a function body can be
null , so you have to check for that explicitly. In Java, this is always not- null , because
it references the instance of a class you’re in. In Kotlin, that’s no longer the case: in an
extension function of a nullable type, this can be null .
p170

Note
When you define your own extension function, you need to consider
whether you should define it as an extension of a nullable type. By
default, define it as an extension of a non- null type. You can safely
change it later (no code will be broken) if it turns out it’s used mostly on
nullable values, and the null value can be reasonably handled.
p171

Note that type parameters are the only exception to the rule that a question mark at
the end is required to mark a type as nullable, and types without a question mark are non-
null .
p172

Java types are represented in Kotlin as platform types, which you can use
either as a nullable type or as a non-null type.
Type(Java) = Type? or Type (Kotlin)
p173

Note that the value-
checking is performed right away when the method is called, not when the parameter is
used. This ensures that incorrect calls are detected early and won’t cause
hard-to-understand exceptions if the null value is accessed after being passed around
between multiple methods in different layers of the codebase.
174

As we said already, you may interpret platform types any way you like—as nullable
or as non- null —so both of the following declarations are valid:
```kotlin
val s: String? = person.name
val s1: String = person.name
```

`:?` The Elvis operator is often used together with the safe-call operator to substitute a
     value other than null when the object on which the method is called is null .

`as?` The as? operator tries to cast a value to the specified type and returns null if the
      value doesn’t have the proper type.

`!!` The not-null assertion is the simplest and bluntest tool Kotlin gives you for dealing with
     a value of a nullable type. It’s represented by a double exclamation mark and converts
     any value to a non- null type. For null values, an exception is thrown.

`let` 'let' executes a lambda only if an expression isn’t null.
      When you need to check multiple values for null , you can use nested let calls to
      handle them. But in most cases, such code ends up fairly verbose and hard to follow. It’s
      generally easier to use a regular if statement to check all the values together.     
      
`lateinit`  Declares a property of a non-null type without an initializer.
            Note that a late-initialized property is always a var , because you need to be able to
            change its value outside of the constructor. 
            If you access the property before it’s been initialized,
             you get an exception "lateinit property myService has not been initialized".     
             

Unlike Java, Kotlin doesn’t differentiate primitive types and wrappers. 
You always use the same type (for example, Int )            
p177

you need to apply the conversion explicitly:
```kotlin
val i = 1
val l: Long = i.toLong()
```
Kotlin makes the conversion explicit in order to avoid surprises, especially when
comparing boxed values.

the 8 primitive value (from java to kotlin)
long    -> Long
char    -> Char
int     -> Int
double  -> Double
float   -> Float
byte    -> Byte
short   -> Short
boolean -> Boolean
p180


Any type is the
Similar to how Object is the root of the class hierarchy in Java, the
supertype of all non-nullable types in Kotlin.
But in Java, Object is a supertype of all
reference types only, and primitive types aren’t part of the hierarchy.
In Kotlin, Any is a supertype of all types, including the primitive types such as Int .
p181

If you need a variable that can hold any possible value in Kotlin, including null ,
you must use the Any? type.
p182

You may wonder why we chose a different name for Unit and didn’t call it Void .
The name Unit is used traditionally in functional languages to mean `“only one instance,”`
and that’s exactly what distinguishes Kotlin’s Unit from Java’s void. We could have
used the customary Void name, but Kotlin has a type called Nothing that performs an
entirely different function. Having two types called Void and Nothing would be
confusing because the meanings are so close.
p183

```kotlin
fun fail(message: String): Nothing{ 
    throw IllegalStateException(message)
}
```
The Nothing type doesn’t have any values, so it only makes sense to use it as a
function return type or as a type argument. In all other cases, declaring a variable where
you can’t store any value doesn’t make sense.


```kotlin
val list :List<Int?>// first case
val list :List<Int>?  // second case

val list :List<Int?>? // maybe you want holds a nullable list of nullable numbers
```
In the first case, the list itself is always not null , but each value in the list can be
null . A variable of the second type may contain a null reference instead of a list
instance, but the elements in the list are guaranteed to be non- null .
p185

The hierarchy of the Kotlin collection interfaces. The Java classes ArrayList
and HashSet extend Kotlin mutable interfaces.
p189
> compare with kotlin.collections.ArrayList that from org.jetbrains.kotlin:kotlin-stdlib-common:1.2.71

Collection-creation-functions(p190)

|Collection type | Read-Only | Mutable |
|:---|:---|:---|
|List| listOf() | arrayListOf()|
|Set | setOf() | hashSetOf(), linkedSetOf(), sortedSetOf()|
|Map | mapOf() | hashMapOf(), linkedMapOf(), sortedMapOf()|


| Read-Only | Mutable |
|:---:| :---:|
| Iterable| MutableIterable|
| Collection | MutableCollection|
| List | MutableList|
| Set | MutableSet |
| - | ArrayList|
| - | HashSet |

Note how the same Java type— List<String> —is represented by two different
Kotlin types: a List<String>? (nullable list of strings) in one case and a
MutableList<String?> (mutable list of nullable strings) in the other. To make these
choices correctly, you must know the exact contract the Java interface or class needs to
follow. As you can see, this is usually easy to understand based on what your
implementation needs to do.
p193

To create an array in Kotlin, you have the following possibilities:
1. The arrayOf() function creates an array containing the elements specified as arguments
to this function.
2. The arrayOfNulls() function creates an array of a given size containing null elements.Of course, it can only be used to create arrays where the element type is nullable;
3. The Array() constructor takes the size of the array and a lambda function, and initializes
   each array element by calling the lambda. This is how you can initialize an array with a
   non-null element type without passing each element explicitly
p193


To represent arrays of primitive types, Kotlin provides a number of separate classes,
one for each primitive type. For example, an array of values of type Int is called
IntArray . For other types, Kotlin provides ByteArray , CharArray , BooleanArray , and
so on. All of these types are compiled to regular Java primitive type arrays, such as
int[] , byte[] , char[] , and so on. Therefore, values in such an array are stored without
boxing, in the most efficient manner possible.
p194

To create an array of a primitive type, you have the following options:
1. The constructor of the type takes a size parameter and returns an array initialized withdefault values for the corresponding primitive type (usually zeros).
2. The factory function ( intArrayOf for IntArray , and so on for other array types) takes a
   variable number of values as arguments and creates an array holding those values.
3. Another constructor takes a size and a lambda used to initialize each element.
p194

