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