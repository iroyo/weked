package devtools

import devtools.inspectedWindow.InspectedWindow
import devtools.network.Network
import devtools.panels.Panels

external class DevTools {
    val inspectedWindow: InspectedWindow
    val network: Network
    val panels: Panels
}