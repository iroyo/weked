@file:Suppress("NOTHING_TO_INLINE")

@Suppress("UnsafeCastFromDynamic")
inline fun <T : Any> jsObject(): T = js("({})")

val <T : Enum<T>> Array<out T>.names get() = this.map { it.name }.toTypedArray()