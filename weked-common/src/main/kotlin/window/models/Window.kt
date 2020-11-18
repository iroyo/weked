package window.models

import tabs.Tab

external interface Window {
    val id: Int?
    val top: Int?
    val left: Int?
    val width: Int?
    val height: Int?
    val focused: Boolean
    val incognito: Boolean
    val alwaysOnTop: Boolean
    val type: String?
    val state: String?
    val title: String?
    val sessionId: String?
    val tabs: Array<Tab>?
}
