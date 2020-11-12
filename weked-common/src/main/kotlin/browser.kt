import tabs.Tabs

/**
 * Firefox API schemas
 * https://github.com/mozilla/gecko-dev/tree/master/browser/components/extensions/schemas
 * https://github.com/mozilla/gecko-dev/tree/master/toolkit/components/extensions/schemas
 */

internal external val browser: Browser

open external class Browser {
    val tabs: Tabs
}
