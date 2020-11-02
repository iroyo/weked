import containers.Containers
import tabs.Tabs

/**
 * Firefox API schemas
 * https://github.com/mozilla/gecko-dev/tree/master/browser/components/extensions/schemas
 * https://github.com/mozilla/gecko-dev/tree/master/toolkit/components/extensions/schemas
 */

external val browser: Browser

external class Browser {
    val contextualIdentities: Containers
    val tabs: Tabs
}
