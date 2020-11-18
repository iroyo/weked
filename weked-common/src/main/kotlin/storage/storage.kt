package storage

import Listener
import kotlin.js.Promise

@Suppress("NOTHING_TO_INLINE")
class Items {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

@Suppress("NOTHING_TO_INLINE")
class Changes {
    inline operator fun get(key: String): StorageChange = asDynamic()[key]
}

external interface StorageChange {
    var oldValue: Any
    var newValue: Any
}

external class StorageArea {

    fun get(key: Array<String>?): Promise<Items>

    fun remove(keys: Array<String>): Promise<Any>

    fun set(items: Items): Promise<Any>

    fun clear(): Promise<Any>

}

external class Storage {

    val onChanged: Listener<(Changes, String) -> Unit>

    var sync: StorageArea

    var local: StorageArea

    var managed: StorageArea
}