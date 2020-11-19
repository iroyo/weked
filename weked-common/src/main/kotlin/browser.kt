import storage.Storage
import tabs.Tabs
import window.Windows

/**
 * Firefox API schemas
 * https://github.com/mozilla/gecko-dev/tree/master/browser/components/extensions/schemas
 * https://github.com/mozilla/gecko-dev/tree/master/toolkit/components/extensions/schemas
 */

internal external val browser: Browser

open external class Browser {
    val tabs: Tabs
    val storage: Storage
    val windows: Windows
}

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
