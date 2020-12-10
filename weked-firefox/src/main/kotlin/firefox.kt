import action.FirefoxBrowserAction
import containers.Containers
import sidebar.Sidebar

external val browser: Firefox

external class Firefox : Browser {
    override val browserAction: FirefoxBrowserAction
    val contextualIdentities: Containers
    val sidebarAction: Sidebar
}