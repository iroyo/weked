import storage.Storage
import tabs.Tabs

/**
 * Firefox API schemas
 * https://github.com/mozilla/gecko-dev/tree/master/browser/components/extensions/schemas
 * https://github.com/mozilla/gecko-dev/tree/master/toolkit/components/extensions/schemas
 */

internal external val browser: Browser

open external class Browser {
    val tabs: Tabs
    val storage: Storage
}

external interface Listener<in T> {
    fun addListener(listener: T)

    fun removeListener(listener: T)

    fun hasListener(listener: T): Boolean
}
