package windows

external interface WindowDimensions {
    var top: Int?
    var left: Int?
    var width: Int?
    var height: Int?
}

external interface WindowCreate {
    var tabId: Int?
    var incognito: Boolean?
    var titlePreface: String?
    var cookieStoreId: String?
    var allowScriptsToClose: Boolean?
}

external interface WindowUpdate {
    var focused: Boolean?
    var drawAttention: Boolean?
    var titlePreface: String?
}

external interface WindowUrls {
    var url: Url?
}

external interface WindowState {
    var state: String?
}

external interface WindowType {
    var type: String?
}

external interface WindowCommonCreate : WindowCreate, WindowType, WindowState, WindowDimensions

external interface WindowCommonUpdate : WindowUpdate, WindowState, WindowDimensions
