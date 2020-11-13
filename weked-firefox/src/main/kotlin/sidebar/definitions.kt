package sidebar

import org.w3c.dom.ImageData

typealias Path = Any
typealias ImageDataType = ImageData

external interface WindowId {
    var windowId: Int?
}

external interface TabId {
    var tabId: Int?
}

external interface Details : WindowId, TabId

external interface TitleProperties : Details {
    var title: String?
}

external interface PanelProperties : Details {
    var panel: String?
}

external interface IconProperties : Details {
    var imageData: ImageDataType?
    val path: Path?
}