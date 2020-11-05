package tabs

external interface Indexable {
    var index: Int?
}

external interface Mutable {
    var muted: Boolean?
}

external interface State {
    var active: Boolean?
    var pinned: Boolean?
}

external interface Common : Indexable, State {
    var url: String?
    var title: String?
    var windowId: Int?
    var openerTabId: Int?
    var cookieStoreId: String?
    var discarded: Boolean?
}

external interface Features {
    var hidden: Boolean?
    var audible: Boolean?
    var attention: Boolean?
    var highlighted: Boolean?
}

external interface BaseTab : Common, Features {
    var status: String?
}