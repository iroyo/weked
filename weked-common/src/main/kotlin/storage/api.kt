package storage

import Listener
import browser
import storage.models.DataUpdate
import storage.models.StorageType
import kotlin.js.Promise

private val api = browser.storage

val <T> ((StorageArea) -> Promise<T>).onLocalStorage get() = this(api.local)
val <T> ((StorageArea) -> Promise<T>).onSyncedStorage get() = this(api.sync)
val <T> ((StorageArea) -> Promise<T>).onManagedStorage get() = this(api.managed)

/**
 * Removes all items from storage.
 */
val clearAllData: (StorageArea) -> Promise<Any> = {
    it.clear()
}

/**
 * Gets all items from storage.
 */
val getAllData: (StorageArea) -> Promise<Items> = {
    it.get(null)
}

/**
 * Gets one or more items from storage.
 */
fun getDataWithKeys(vararg keys: String): (StorageArea) -> Promise<Items> = {
    it.get(arrayOf(*keys))
}

/**
 * Removes one or more items from storage.
 */
fun removeDataWithKeys(vararg keys: String): (StorageArea) -> Promise<Any> = {
    it.remove(arrayOf(*keys))
}

/**
 * Sets multiple items.
 */
fun saveData(vararg items: Pair<String, Any>): (StorageArea) -> Promise<Any> = {
    it.set(Items().apply {
        items.forEach { pair -> set(pair.first, pair.second) }
    })
}

/**
 * Fired when one or more items change.
 */
val onDataChanged = object: Listener<(DataUpdate) -> Unit> {
    override fun addListener(listener: (DataUpdate) -> Unit) = api.onChanged.addListener { changes, type ->
        listener(dataUpdate(changes, type))
    }

    override fun removeListener(listener: (DataUpdate) -> Unit) = api.onChanged.removeListener { changes, type ->
        listener(dataUpdate(changes, type))
    }

    override fun hasListener(listener: (DataUpdate) -> Unit) = api.onChanged.hasListener { changes, type ->
        listener(dataUpdate(changes, type))
    }

    private fun dataUpdate(changes: Changes, type: String) = DataUpdate(StorageType.valueOf(type.toUpperCase()), changes)
}

