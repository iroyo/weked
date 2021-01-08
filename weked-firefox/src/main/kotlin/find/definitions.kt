package find

external interface HighlightOptions {
    var tabId: Int
    var rangeIndex: Int
    var noScroll: Boolean
}

external interface FindOptions {
    var tabId: Int
    var entireWord: Boolean
    var caseSensitive: Boolean
    var includeRectData: Boolean
    var includeRangeData: Boolean
}

external interface FindResult {
    val count: Int
    val rangeData: Array<RangeData>?
    val rectData: Array<RangeData>?
}

external interface RangeData {
    val framePos: Int
    val startTextNodePos: Int
    val endTextNodePos: Int
    val startOffset: Int
    val endOffset: Int
    val text: String
}

external interface RectData {
    val rectsAndTexts: RectsAndTexts
    val text: String
}

external interface RectsAndTexts {
    val rectList: Array<RectItem>
    val textList: Array<String>
}

external interface RectItem {
    val top: Int
    val left: Int
    val bottom: Int
    val right: Int
}