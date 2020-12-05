package windows.models

import tabs.Tab
import windows.Window
import windows.WindowBase
import windows.windowType

class BrowserWindow(window: Window): WindowBase by window {

    val type: WindowMode = window.windowType
    val state: BrowserWindowState? = BrowserWindowState.create(window.state)

    val tabs: Array<Tab> = window.tabs ?: emptyArray()
}