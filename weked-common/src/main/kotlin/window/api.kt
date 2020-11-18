package window

import browser
import jsObject
import window.models.WindowMode
import window.models.WindowNormalState
import window.models.WindowState
import window.models.WindowState.*

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
 * Gets all the windows that match the [WindowMode].
 */
val getAllWindows get() = getAllWindows(WindowMode.NORMAL, WindowMode.PANEL, WindowMode.POPUP)

/**
 * Creates (opens) a new browser with any optional sizing, position or default URL provided.
 */
private fun createWindow(state: String, block: WindowCommonCreate.() -> Unit, vararg url: String) =
    api.create(jsObject<CreateProperties>()
        .apply(block)
        .apply {
            this.url = url
            this.state = state
        }
    )

fun createWindow(
    vararg url: String,
    windowState: WindowNormalState = WindowNormalState.NORMAL,
    block: WindowCommonCreate.() -> Unit = {}
) = createWindow(windowState.name.toLowerCase(), block, *url)

fun createMinimizedWindow(vararg url: String, block: WindowData.() -> Unit = {}) =
    createWindow(MINIMIZED.string, block, *url)

fun createMaximizedWindow(vararg url: String, block: WindowData.() -> Unit = {}) =
    createWindow(MAXIMIZED.string, block, *url)

fun createFullscreenWindow(vararg url: String, block: WindowData.() -> Unit = {}) =
    createWindow(FULLSCREEN.string, block, *url)

private fun updateWindow(id: Int, state: String?, block: WindowCommonUpdate.() -> Unit) = api.update(id, jsObject<UpdateProperties>()
    .apply(block)
    .apply { state?.let { this.state = it } }
)

/**
 * Updates the properties of a window. Specify only the properties that you want to change; unspecified properties will be left unchanged.
 * @throws InvalidWindowId when the id provided does not much any windowId
 */
fun updateWindow(windowId: Int, state: WindowNormalState? = null, block: WindowCommonUpdate.() -> Unit = {}) =
    updateWindow(windowId, state?.name?.toLowerCase(), block)

/**
 * Updates the properties of a window. Specify only the properties that you want to change; unspecified properties will be left unchanged.
 * @throws InvalidWindowId when the id provided does not much any windowId
 */
fun updateWindow(windowId: Int, state: WindowState, block: WindowUpdate.() -> Unit = {}) =
    updateWindow(windowId, state.name.toLowerCase(), block)