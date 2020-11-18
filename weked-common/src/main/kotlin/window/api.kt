package window

import browser
import jsObject
import window.models.WindowMode
import window.models.WindowState

private val api = browser.windows

private fun createGetProperties(populateTabs: Boolean) = jsObject<GetProperties>().apply {
    populate = populateTabs
}

/**
 * Gets details about a window.
 * @throws InvalidWindowId when the id provided does not much any windowId
 */
fun getWindow(id: Int, populateTabs: Boolean = false) = api.get(id, createGetProperties(populateTabs))

/**
 * Gets the current window.
 */
fun getCurrentWindow(populateTabs: Boolean = false) = api.getCurrent(createGetProperties(populateTabs))

/**
 * Gets the window that was most recently focused &mdash; typically the window 'on top'.
 */
fun getLastFocusedWindow(populateTabs: Boolean = false) = api.getLastFocused(createGetProperties(populateTabs))

/**
 * Gets all the windows that match the [WindowMode].
 */
fun getAllWindows(vararg windowMode: WindowMode) = api.getAll(jsObject<GetProperties>().apply {
    windowTypes = windowMode.map { it.name.toLowerCase() }.toTypedArray()
})

/**
 * Creates (opens) a new browser with any optional sizing, position or default URL provided.
 */
private fun createWindow(windowState: WindowState, block: WindowCommon.() -> Unit, vararg url: String) = api.create(jsObject<CreateProperties>()
    .apply(block)
    .apply {
        this.url = url
        this.state = windowState.name.toLowerCase()
    }
)

fun createWindow(vararg url: String, block: WindowCommon.() -> Unit = {}) = createWindow(WindowState.NORMAL, block, *url)

fun createMinimizedWindow(vararg url: String, block: WindowData.() -> Unit = {}) = createWindow(WindowState.MINIMIZED, block, *url)
fun createMaximizedWindow(vararg url: String, block: WindowData.() -> Unit = {}) = createWindow(WindowState.MAXIMIZED, block, *url)
fun createFullscreenWindow(vararg url: String, block: WindowData.() -> Unit = {}) = createWindow(WindowState.FULLSCREEN, block, *url)