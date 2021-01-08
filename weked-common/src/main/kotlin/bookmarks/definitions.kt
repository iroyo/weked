package bookmarks

typealias BookmarkTreeNodeUnmodifiable = String

typealias BookmarkTreeNodeType = String

external interface Indexable {
    var index: Int?
    var parentId: String?
}

external interface UrlProvider {
    var url: String?
}

external interface TitleProvider {
    var title: String?
}

external interface BaseData: UrlProvider, TitleProvider

external interface QueryData : BaseData {
    var query: String?
}

external interface ReorderData {
    val childIds: Array<String>
}

external interface MoveData: Indexable {
    val oldParentId: String
    val oldIndex: Int
}

external interface RemoveData: Indexable {
    val node: BookmarkTreeNode
}

external interface CreateFolderDetails : Indexable, TitleProvider

external interface CreateBookmarkDetails : CreateFolderDetails, UrlProvider

external interface CommonData : CreateBookmarkDetails {
    var type: BookmarkTreeNodeType?
}

external interface BookmarkTreeNode: CommonData {
    val id: String?
    val dateAdded: Double?
    val dateGroupModified: Double?
    val children: Array<BookmarkTreeNode>?
    val unmodifiable: BookmarkTreeNodeUnmodifiable?
}