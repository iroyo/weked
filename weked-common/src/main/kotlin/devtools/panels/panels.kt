package devtools.panels

import kotlin.js.Promise

open external class Panels {

    val themeName: String
    val elements: ElementsPanel

    fun create(title: String, iconPath: String, pagePath: String): Promise<ExtensionPanel>

}