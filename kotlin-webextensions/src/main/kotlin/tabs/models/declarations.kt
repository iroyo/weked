package tabs.models

external interface TabData {
    var title: String?
    var url: String?
}

external interface TabState {
    var active: Boolean?
    var pinned: Boolean?
    var discarded: Boolean?
}

external interface TabFeatures : TabState {
    var muted: Boolean?
    var hidden: Boolean?
    var audible: Boolean?
    var attention: Boolean?
    var highlighted: Boolean?
}

external interface TabDimensions {
    val width: Int?
    val height: Int?
}

external interface TabHardware {
    var camera: Boolean?
    var microphone: Boolean?
}

external interface Tab : TabData, TabFeatures, TabDimensions {
    val id: Int?
    val index: Int
    val windowId: Int?
    val openerTabId: Int?
    val successorTabId: Int?
    val incognito: Boolean?
    val lastAccessed: Int?
    val favIconUrl: String?
    val status: String?
    val sessionId: String?
    val cookieStoreId: String?
    val isArticle: Boolean?
    val isInReaderMode: Boolean?
}

external interface WindowType {
    var windowType: String?
}

external interface StatusType {
    var status: String? // ENUM
}

external interface SharingType {
    var screen: Any? // ENUM
}

external interface QueryBase : TabData, TabFeatures, TabHardware {
    var index: Int?
    var windowId: Int?
    var openerTabId: Int?
    var cookieStoreId: String?
    var currentWindow: Boolean?
    var lastFocusedWindow: Boolean?
}

external interface TabQuery : QueryBase, StatusType, SharingType, WindowType

external interface TabProperties : TabData, TabState {
    var index: Int?
    var windowId: Int?
    var openerTabId: Int?
    var cookieStoreId: String?
}

external interface TabDuplicate {
    var index: Int?
    var active: Boolean?
}