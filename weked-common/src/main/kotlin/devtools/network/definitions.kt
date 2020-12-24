package devtools.network

import kotlin.js.Promise

external class Request {
    /**
     * Returns content of the response body.
     */
    fun getContent(): Promise<String>
}

@Suppress("NOTHING_TO_INLINE")
class HarLog() {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}