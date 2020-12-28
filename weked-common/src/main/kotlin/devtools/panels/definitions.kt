package devtools.panels

import SimpleListener
import SingleParameterCallback
import org.w3c.dom.Window
import kotlin.js.Promise


external class ElementsPanel {
    /**
     * Fires when the user selects a different page element for inspection with the browser's developer tools,
     */
    val onSelectionChanged: SimpleListener

    /**
     * Creates a pane in the inspector's sidebar.
     */
    fun createSidebarPane(title: String): Promise<ExtensionSidebarPane>
}

external class ExtensionPanel {
    val onHidden: SimpleListener
    val onShown: SingleParameterCallback<Window>

    fun createStatusBarButton(iconPath: String, tooltipText: String, disabled: Boolean): Button
}

external class ExtensionSidebarPane {
    val onHidden: SimpleListener
    val onShown: SingleParameterCallback<Window>

    fun setPage(path: String): Promise<Unit>
    fun setObject(json: Any, rootTitle: String? = definedExternally): Promise<Unit>
    fun setExpression(expression: String, rootTitle: String? = definedExternally): Promise<Unit>

}

external class Button {
    val onClicked: SimpleListener

    fun update(iconPath: String?, tooltipText: String?, disabled: Boolean?)
}