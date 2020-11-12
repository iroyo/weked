import containers.Containers
import sidebar.Sidebar

external val browser: Firefox

external class Firefox : Browser {
    val contextualIdentities: Containers
    val sidebarAction: Sidebar
}