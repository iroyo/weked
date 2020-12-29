@file:Suppress("NOTHING_TO_INLINE")

@Suppress("UnsafeCastFromDynamic")
inline fun <T : Any> jsObject(): T = js("({})")

inline fun <reified T : Enum<T>> enumFrom(value: String?, fallback: T) = value?.let {
    enumValueOf<T>(it.toUpperCase())
} ?: fallback

val <T : Enum<T>> Array<out T>.names get() = this.map { it.name }.toTypedArray()

fun <T : Any> create(block: T.() -> Unit) = jsObject<T>().apply(block)

fun <T, R> Array<out T>.mapToArray(transform: (T) -> R): Array<R> = map(transform).toTypedArray()