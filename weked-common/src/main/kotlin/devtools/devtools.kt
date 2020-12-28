package devtools

import devtools.inspectedWindow.InspectedWindow
import devtools.network.Network
import devtools.panels.Panels

open external class DevTools {
    val inspectedWindow: InspectedWindow
    val network: Network
    open val panels: Panels
}