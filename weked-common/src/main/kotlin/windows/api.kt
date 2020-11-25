package windows

import CallbackListener
import browser
import jsObject
import names
import windows.models.*
import windows.models.WindowState

private val api = browser.windows

private fun createGetProperties(populateTabs: Boolean, types: Array<String>? = null) = jsObject<GetProperties>().apply {
    populate = populateTabs
    if (!windowTypes.isNullOrEmpty()) windowTypes = types
}

private fun createEventFilter(windowMode: Array<out WindowMode>) = jsObject<EventFilter>().apply {
    windowTypes = windowMode.names
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

/**
 * Fired when a window is created.
 * [BrowserWindow] Details of the window that was created. */
val onWindowCreated = object : CallbackListener<BrowserWindow> {

    private val event = api.onCreated

    override fun addListener(listener: (BrowserWindow) -> Unit) = event.addListener { listener(BrowserWindow(it)) }

    override fun removeListener(listener: (BrowserWindow) -> Unit) = event.removeListener { listener(BrowserWindow(it)) }

    override fun hasListener(listener: (BrowserWindow) -> Unit) = event.hasListener { listener(BrowserWindow(it)) }
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
fun getAllWindows(populateTabs: Boolean = false, vararg windowMode: WindowMode) =
    api.getAll(createGetProperties(populateTabs, windowMode.names)).then { it.map(::BrowserWindow) }

/**
 * Gets all the windows that match the [WindowMode].
 */
val getAllWindows get() = getAllWindows(false, WindowMode.NORMAL, WindowMode.POPUP, WindowMode.DEVTOOLS)

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