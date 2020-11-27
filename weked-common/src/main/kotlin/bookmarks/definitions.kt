package bookmarks

typealias BookmarkTreeNodeUnmodifiable = String

typealias BookmarkTreeNodeType = String

external interface BookmarkTreeNode {
    val index: Int?
    val id: String?
    val parentId: String?
    val url: String?
    val title: String
    val dateAdded: Double?
    val dateGroupModified: Double?
    val type: BookmarkTreeNodeType?
    val children: Array<BookmarkTreeNode>
    val unmodifiable: BookmarkTreeNodeUnmodifiable?
}