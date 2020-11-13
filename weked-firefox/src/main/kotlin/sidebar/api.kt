package sidebar

import browser
import jsObject

internal val api = browser.sidebarAction

/**
 * Opens the extension sidebar in the active window.
 */
fun openSidebar() = api.open()

/**
 * Closes the extension sidebar in the active window if the sidebar belongs to the extension.
 */
fun closeSidebar() = api.close()

/**
 * Toggles the extension sidebar in the active window.
 */
fun toggleSidebar() = api.toggle()

/**
 * Checks whether the sidebar action is open.
 */
fun isSidebarOpen(id: Int? = null) = api.isOpen(jsObject<WindowId>().apply {
    windowId = id
})

/**
 * Gets the url to the html document set as the panel for this sidebar action
 * @throws InvalidTabId when tabId is not found
 * @throws InvalidWindowId when windowId is not found
 */
fun getSidebarPanel(block: Details.() -> Unit = {})  = api.getPanel(jsObject<Details>().apply(block))

fun setSidebarPanel(block: PanelProperties.() -> Unit = {})  = api.setPanel(jsObject<PanelProperties>().apply(block))

/**
 * Gets the title for this sidebar action
 * @throws InvalidTabId when tabId is not found
 * @throws InvalidWindowId when windowId is not found
 */
fun getSidebarTitle(block: Details.() -> Unit = {})  = api.getTitle(jsObject<Details>().apply(block))

fun setSidebarTitle(block: TitleProperties.() -> Unit = {})  = api.setTitle(jsObject<TitleProperties>().apply(block))

fun setSidebarIcon(block: IconProperties.() -> Unit = {})  = api.setIcon(jsObject<IconProperties>().apply(block))