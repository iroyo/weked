
external interface Delta<T> {
    var current:T?
    var previous: T?
}

typealias StringDelta = Delta<String>
typealias DoubleDelta = Delta<Double>
typealias BooleanDelta = Delta<Boolean>