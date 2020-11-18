package window

typealias Url = Any

external interface GetProperties {
    var populate: Boolean?
    var windowTypes: Array<String>?
}

external interface WindowDimensions {
    var top: Int?
    var left: Int?
    var width: Int?
    var height: Int?
}

external interface WindowData {
    var tabId: Int?
    var incognito: Boolean?
    var type: String?
    var titlePreface: String?
    var cookieStoreId: String?
    var allowScriptsToClose: Boolean?
}

external interface WindowUrls {
    var url: Url?
}

external interface WindowCommonCreate : WindowData, WindowDimensions

external interface CreateProperties : WindowCommonCreate, WindowUrls {
    var state: String?
}

external interface WindowUpdate {
    var focused: Boolean?
    var drawAttention: Boolean?
    var titlePreface: String?
}

external interface WindowCommonUpdate : WindowUpdate, WindowDimensions

external interface UpdateProperties : WindowCommonUpdate {
    var state: String?
}