import action.FirefoxBrowserAction
import containers.Containers
import settings.FirefoxBrowserSettings
import sidebar.Sidebar

external val browser: Firefox

external class Firefox : Browser {
    override val browserAction: FirefoxBrowserAction
    val browserSettings: FirefoxBrowserSettings
    val contextualIdentities: Containers
    val sidebarAction: Sidebar
}