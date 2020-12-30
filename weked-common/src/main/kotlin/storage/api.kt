package storage

import Listener
import browser
import storage.models.DataUpdate
import storage.models.StorageType
import kotlin.js.Promise

private val api = browser.storage

class StorageResolver<T>(private val block: (StorageArea) -> Promise<T>) {
    val onLocalStorage get() = block(api.local)
    val onSyncedStorage get() = block(api.sync)
    val onManagedStorage get() = block(api.managed)
}

/**
 * Removes all items from storage.
 */
val clearAllData = StorageResolver { it.clear() }

/**
 * Gets all items from storage.
 */
val getAllData = StorageResolver { it.get(null) }

/**
 * Gets one or more items from storage.
 */
fun getDataWithKeys(vararg keys: String) = StorageResolver {
    it.get(arrayOf(*keys))
}

/**
 * Removes one or more items from storage.
 */
fun removeDataWithKeys(vararg keys: String) = StorageResolver {
    it.remove(arrayOf(*keys))
}

/**
 * Sets multiple items.
 */
fun saveData(vararg items: Pair<String, Any>) = StorageResolver {
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

