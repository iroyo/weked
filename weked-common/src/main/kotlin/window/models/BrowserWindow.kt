package window.models

import tabs.Tab
import window.Window
import window.WindowBase
import window.windowType

class BrowserWindow(window: Window): WindowBase by window {

    val type: WindowMode = window.windowType
    val state: WindowState? = WindowState.create(window.state)

    val tabs: Array<Tab> = window.tabs ?: emptyArray()
}