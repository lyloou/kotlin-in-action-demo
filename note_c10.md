```kotlin
annotation class JsonExclude
```
Since annotation classes are only used to define the
structure of metadata associated with declarations and expressions, they cannot contain
any code. Therefore, the compiler prohibits specifying a body for an annotation class.
p296

```kotlin
annotation class JsonName(val name: String)

/* Java : declare the same annotation in Java*/
public @interface JsonName{
    String value();
}
```
You’re using the regular primary constructor declaration syntax. The val keyword is
mandatory for all parameters of an annotation class.
p296

Just like in Java, a Kotlin annotation class can itself be annotated. The annotations which
can be applied to annotation classes are called meta-annotations
p297

The @Target meta-annotation specifies the types of elements to which the annotation
can be applied. If you don’t use it, the annotation will be applicable to all declarations.
p297


Note
Java by default retains annotations in .class files but
does not make them accessible at runtime. Most annotations do need to
be present at runtime, so in Kotlin the default is different: annotations
have RUNTIME retention. Therefore, we didn’t need to specify the retention
for JKid annotations explicitly.
p297

Reflection is, simply put, a way to access properties and methods of objects dynamically
at runtime, without knowing in advance what those properties are
p300


The following picture shows a hierarchy of interfaces that you can use to access
source code elements at runtime.
```
//  Hierarchy of interfaces in the Kotlin reflection API

KAnnotatedElement
    --> KClass
    --> KParameter
    --> KCallable
        --> KFunction
            --> KFunction0
            --> KFunction1
            --> KFunction2
            --> KProperty.Getter
            --> KMutableProperty.Setter
        --> KProperty
            --> KMutableProperty
```
p304



The JSON deserializer in JKid is implemented in a fairly conventional way and
consists of three main stages:
1. lexical analyzer (usually referred to as lexer),
2. syntax analyzer or parser,
3. and the deserialization component itself.
p311

