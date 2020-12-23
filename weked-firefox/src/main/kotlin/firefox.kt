import action.FirefoxBrowserAction
import commands.FirefoxCommands
import containers.Containers
import portal.CaptivePortal
import settings.FirefoxBrowserSettings
import sidebar.Sidebar

external val browser: Firefox

external class Firefox : Browser {
    override val browserAction: FirefoxBrowserAction
    override val commands: FirefoxCommands
    val browserSettings: FirefoxBrowserSettings
    val contextualIdentities: Containers
    val captivePortal: CaptivePortal
    val sidebarAction: Sidebar
}