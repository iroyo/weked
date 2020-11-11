@file:Suppress("NOTHING_TO_INLINE")

inline fun <T : Any> jsObject(): T = js("({})")