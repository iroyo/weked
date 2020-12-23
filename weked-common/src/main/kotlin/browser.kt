import action.BrowserAction
import alarms.Alarms
import bookmarks.Bookmarks
import commands.Commands
import cookies.Cookies
import data.BrowserData
import kotlinx.browser.window
import storage.Storage
import tabs.Tabs
import windows.Windows

/**
 * Firefox API schemas
 * https://github.com/mozilla/gecko-dev/tree/master/browser/components/extensions/schemas
 * https://github.com/mozilla/gecko-dev/tree/master/toolkit/components/extensions/schemas
 */

internal external val browser: Browser

val isFirefox = window.navigator.userAgent.contains("Firefox", ignoreCase = true)
val isChrome = window.navigator.userAgent.contains("Chrome", ignoreCase = true)

open external class Browser {
    val tabs: Tabs
    val alarms: Alarms
    val storage: Storage
    val windows: Windows
    val cookies: Cookies
    val bookmarks: Bookmarks
    open val commands: Commands
    open val browsingData: BrowserData
    open val browserAction: BrowserAction
}