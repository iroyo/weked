package tabs


external interface MutedInfo : Mutable {
    val reason: String?
    val extensionId: String?
}

external interface TabDuplicate : Indexable {
    var active: Boolean?
}

external interface TabCreate : Common {
    var openInReaderMode: Boolean?
}

external interface TabUpdate : State, Mutable {
    var url: String?
    var highlighted: Boolean?
    var loadReplace: Boolean?
    var successorTabId: Int?
    var openerTabId: Int?
}

external interface TabQuery : BaseTab, Mutable {
    var screen: Any?
    var windowType: String?
    var currentWindow: Boolean?
    var lastFocusedWindow: Boolean?
    var microphone: Boolean?
    var camera: Boolean?
}

external interface Tab : BaseTab {
    val id: Int?
    val successorTabId: Int?
    val lastAccessed: Int?
    val favIconUrl: String?
    val sessionId: String?
    val incognito: Boolean
    val isArticle: Boolean?
    val isInReaderMode: Boolean?
    var mutedInfo: MutedInfo?
    val width: Int?
    val height: Int?
}