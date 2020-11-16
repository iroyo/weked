package storage

import kotlin.js.Promise

@Suppress("NOTHING_TO_INLINE")
class Items {
    inline operator fun get(key: String): dynamic = asDynamic()[key]
    inline operator fun set(key: String, value: dynamic) {
        asDynamic()[key] = value
    }
}

external class StorageArea {

    fun remove(key: String): Promise<Any>

    fun remove(keys: Array<String>): Promise<Any>

    fun get(key: String?): Promise<Items>

    fun get(key: Array<String>): Promise<Items>

    fun set(items: Items): Promise<Any>

    fun clear(): Promise<Any>

}

external class Storage {

    var sync: StorageArea

    var local: StorageArea

    var managed: StorageArea
}