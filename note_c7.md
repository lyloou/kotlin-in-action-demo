Overloadable binary arithmetic operators

| Expression | Function name |
| :--- | :--- |
| a * b | times |
| a / b | div |
| a % b | mod |
| a + b | plus |
| a - b | minus |

Operators for your own types always use the same precedence as the standard
numeric types. For example, if you write a + b * c , the multiplication will always be
executed before the addition, even if you’ve defined those operators yourself. The
operators * , / , and % have the same priority, which is higher than the priority of the + and
- operators.
p199


Overloadable unary arithmetic operators

| Expression | Function name |
| :--- | :--- |
| +a | unaryPlus |
| -a | unaryMinus |
| !a | not |
| ++a, a++ | inc |
| --a, a-- | dec |

p204


The identity (`===`) equals
operator does exactly the same thing as the == operator in Java: it checks that both of its
arguments reference the same object


```kotlin
@Test
fun testRange() {
    print("10 in 2 until 10: ${10 in 2 until 10}")
    print("\nuntil: ")
    for (i in 2 until 10) { // not include 10
        print("$i ")
    }

    print("\nrange: ")
    for (i in range(2, 10)) { // not include 10
        print("$i ")
    }

    print("\nrangeClosed: ")
    for (i in rangeClosed(2, 10)) { // include 10
        print("$i ")
    }

    print("\n..: ")
    for (i in 2..10) { // include 10
        print("$i ")
    }
}
```
p210


A destructuring declaration looks like a regular variable declaration, but it has
multiple variables grouped in parentheses.
p213

For a data class, the compiler generates a componentN function for every property
declared in the primary constructor,
```kotlin
data class Person(val name: String, val age: Int)
var person = Person("lou", 18)
val (x1, y1) = person
println("$x1, $y1")
println("${person.component1()}, ${person.component2()}")
```
