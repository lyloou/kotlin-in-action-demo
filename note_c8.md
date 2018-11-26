
```kotlin
fun performRequest(callback: (x: Int, y: Int) -> Int): Int {
    return callback(1, 2)
}
println(performRequest({ x, y -> x * y }))
println(performRequest({ x, y -> x - y }))
println(performRequest { x, y -> x + y })
```


As you can see, function types can help eliminate code duplication.
If you’re tempted to copy and paste a piece of the code, it’s likely that the duplication can be avoided
With lambdas, you can extract not only the data that’s repeated, but the behavior as well
p239


Using the inline keyword can improve performance only with
functions that take lambdas as parameters.(All other cases require additional measuring and investigation.)
p244


You can verify for yourself that the inline functions in the Kotlin standard library are
always small.
p244