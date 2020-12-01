package bookmarks

typealias BookmarkTreeNodeUnmodifiable = String

typealias BookmarkTreeNodeType = String

external interface Indexable {
    var index: Int?
    var parentId: String?
}

external interface BaseData {
    var url: String?
    var title: String?
}

external interface CommonData : Indexable, BaseData {
    var type: BookmarkTreeNodeType?
}

external interface BookmarkTreeNode: CommonData {
    val id: String?
    val dateAdded: Double?
    val dateGroupModified: Double?
    val children: Array<BookmarkTreeNode>
    val unmodifiable: BookmarkTreeNodeUnmodifiable?
}