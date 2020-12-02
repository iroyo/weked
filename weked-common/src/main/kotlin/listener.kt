typealias SingleParameterCallback<T> = Listener<(T) -> Unit>
typealias DualParameterCallback<T, R> = Listener<(T, R) -> Unit>

typealias SimpleListener = Listener<() -> Unit>

external interface Listener<in T> {
    fun addListener(listener: T)

    fun removeListener(listener: T)

    fun hasListener(listener: T): Boolean
}

external interface FilterableListener<in T, in F> {
    fun addListener(listener: T, filter: F)

    fun removeListener(listener: T)

    fun hasListener(listener: T): Boolean
}

class CustomFilterListener<T, F>(private val filterableListener: FilterableListener<T, F>) {
    fun addListener(filter: F, listener: T) = filterableListener.addListener(listener, filter)

    fun removeListener(listener: T) = filterableListener.removeListener(listener)

    fun hasListener(listener: T) = filterableListener.hasListener(listener)
}