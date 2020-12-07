import alarms.Alarms
import bookmarks.Bookmarks
import storage.Storage
import tabs.Tabs
import windows.Windows

/**
 * Firefox API schemas
 * https://github.com/mozilla/gecko-dev/tree/master/browser/components/extensions/schemas
 * https://github.com/mozilla/gecko-dev/tree/master/toolkit/components/extensions/schemas
 */

internal external val browser: Browser

open external class Browser {
    val tabs: Tabs
    val alarms: Alarms
    val storage: Storage
    val windows: Windows
    val bookmarks: Bookmarks
}