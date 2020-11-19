package window

import FilterableListener
import browser
import jsObject
import window.models.*
import window.models.WindowState

private val api = browser.windows

private fun createGetProperties(populateTabs: Boolean) = jsObject<GetProperties>().apply {
    populate = populateTabs
}

var WindowType.windowType: WindowMode
    get() = type?.let { WindowMode.valueOf(it.toUpperCase()) } ?: WindowMode.NORMAL
    set(value) {
        type = value.name.toLowerCase()
    }

/**
 * Fired when a window is removed (closed).
 */
val onWindowRemoved = api.onRemoved

/**
 * Fired when the currently focused window changes. Will be $(ref:windows.WINDOW_ID_NONE) if all browser windows have lost focus.
 * Note: On some Linux window managers, WINDOW_ID_NONE will always be sent immediately preceding a switch from one browser window to another.
 */
val onWindowFocusChanged = api.onFocusChanged


class FilterListener : FilterableListener<(BrowserWindow) -> Unit, Array<WindowMode>> {


    override fun removeListener(listener: (BrowserWindow) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun hasListener(listener: (BrowserWindow) -> Unit): Boolean {
        TODO("Not yet implemented")
    }

    fun addListener(filter: Array<WindowMode>, listener: (BrowserWindow) -> Unit) {

    }

    override fun addListener(listener: (BrowserWindow) -> Unit, filter: Array<WindowMode>) {
        TODO("Not yet implemented")
    }

}
/**
 * Fired when a window is created.
 * [BrowserWindow] Details of the window that was created. */
val onWindowCreated = object : FilterableListener<(BrowserWindow) -> Unit, EventFilter> {
    override fun addListener(listener: (BrowserWindow) -> Unit, filter: EventFilter) = api.onCreated.addListener(
        { listener(BrowserWindow(it)) }, filter
    )

    override fun removeListener(listener: (BrowserWindow) -> Unit) = api.onCreated.removeListener {
        listener(BrowserWindow(it))
    }

    override fun hasListener(listener: (BrowserWindow) -> Unit) = api.onCreated.hasListener {
        listener(BrowserWindow(it))
    }

}

/**
 * Gets details about a window.
 * @throws InvalidWindowId when the id provided does not much any windowId
 */
fun getWindow(id: Int, populateTabs: Boolean = false) =
    api.get(id, createGetProperties(populateTabs)).then(::BrowserWindow)

/**
 * Gets the current window.
 */
fun getCurrentWindow(populateTabs: Boolean = false) =
    api.getCurrent(createGetProperties(populateTabs)).then(::BrowserWindow)

/**
 * Gets the window that was most recently focused &mdash; typically the window 'on top'.
 */
fun getLastFocusedWindow(populateTabs: Boolean = false) =
    api.getLastFocused(createGetProperties(populateTabs)).then(::BrowserWindow)

/**
 * Gets all the windows that match the [WindowMode].
 */
fun getAllWindows(vararg windowMode: WindowMode) = api.getAll(jsObject<GetProperties>().apply {
    windowTypes = windowMode.map { it.name.toLowerCase() }.toTypedArray()
}).then { it.map(::BrowserWindow) }

/**
 * Gets all the windows that match the [WindowMode].
 */
val getAllWindows get() = getAllWindows(WindowMode.NORMAL, WindowMode.POPUP)

/**
 * Creates (opens) a new browser with any optional sizing, position or default URL provided.
 */
private fun createWindow(state: WindowState, block: WindowCommonCreate.() -> Unit, vararg url: String) =
    api.create(jsObject<CreateProperties>()
        .apply(block)
        .apply {
            this.url = url
            this.state = state.name
        }
    ).then(::BrowserWindow)

fun createWindow(
    vararg url: String,
    windowState: ResizableWindowState = ResizableWindowState.Normal,
    block: WindowCommonCreate.() -> Unit = {}
) = createWindow(windowState, block, *url)

fun createMinimizedWindow(vararg url: String, block: WindowCreate.() -> Unit = {}) =
    createWindow(UnresizableWindowState.Minimized, block, *url)

fun createMaximizedWindow(vararg url: String, block: WindowCreate.() -> Unit = {}) =
    createWindow(UnresizableWindowState.Maximized, block, *url)

fun createFullscreenWindow(vararg url: String, block: WindowCreate.() -> Unit = {}) =
    createWindow(UnresizableWindowState.Fullscreen, block, *url)

private fun updateWindow(id: Int, state: WindowState?, block: WindowCommonUpdate.() -> Unit) =
    api.update(id, jsObject<UpdateProperties>()
        .apply(block)
        .apply { state?.let { this.state = it.name } }
    ).then(::BrowserWindow)

/**
 * Updates the properties of a window. Specify only the properties that you want to change; unspecified properties will be left unchanged.
 * @throws InvalidWindowId when the id provided does not much any windowId
 */
fun updateWindow(windowId: Int, state: ResizableWindowState? = null, block: WindowCommonUpdate.() -> Unit = {}) =
    updateWindow(id = windowId, state, block)

/**
 * Updates the properties of a window. Specify only the properties that you want to change; unspecified properties will be left unchanged.
 * @throws InvalidWindowId when the id provided does not much any windowId
 */
fun updateWindow(windowId: Int, state: UnresizableWindowState, block: WindowUpdate.() -> Unit = {}) =
    updateWindow(id = windowId, state, block)

/**
 * Removes (closes) a window, and all the tabs inside it.
 */
fun removeWindow(windowId: Int) = api.remove(windowId)