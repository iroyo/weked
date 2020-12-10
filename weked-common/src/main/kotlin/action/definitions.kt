package action

import org.w3c.dom.ImageData

typealias ImageDataType = ImageData

typealias ColorArray = Array<Int>

external interface TabId {
    var tabId: Int?
}

external interface TitleData : TabId {
    var title: String?
}

external interface IconDataViaImageData : TabId {
    var imageData: ImageDataType?
}

external interface IconDataViaPath : TabId {
    var path: String?
}

external interface PopupData : TabId {
    var popup: String?
}

external interface BadgeTextData: TabId {
    var text: String?
}

external interface BadgeColorStringData: TabId {
    var color: String?
}

external interface BadgeColorArrayData: TabId {
    var color: ColorArray?
}