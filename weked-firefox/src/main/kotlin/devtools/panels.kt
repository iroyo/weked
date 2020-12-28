package devtools

import SingleParameterCallback
import devtools.panels.Panels

external class FirefoxPanels : Panels {
    val onThemeChanged: SingleParameterCallback<String>
}