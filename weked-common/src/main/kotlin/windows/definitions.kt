package windows

import tabs.Tab

typealias Url = Any

external interface GetProperties {
    var populate: Boolean?
    var windowTypes: Array<String>?
}

external interface EventFilter {
    var windowTypes: Array<String>?
}

external interface CreateProperties : WindowCommonCreate, WindowUrls

external interface UpdateProperties : WindowCommonUpdate

external interface WindowBase : WindowDimensions {
    val id: Int?
    val focused: Boolean
    val incognito: Boolean
    val alwaysOnTop: Boolean
    val title: String?
    val sessionId: String?
}

external interface Window: WindowBase, WindowState, WindowType {
    val tabs: Array<Tab>?
}