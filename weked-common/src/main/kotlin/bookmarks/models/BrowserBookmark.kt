package bookmarks.models

import bookmarks.BookmarkTreeNode
import bookmarks.CreateBookmarkDetails
import kotlin.js.Date

class BrowserBookmark(
    bookmarkTreeNode: BookmarkTreeNode
) : CreateBookmarkDetails by bookmarkTreeNode {

    val id: String? = bookmarkTreeNode.id

    val dateAdded: Date? = bookmarkTreeNode.dateAdded?.let { Date(it) }
    val dateGroupModified: Date? = bookmarkTreeNode.dateGroupModified?.let { Date(it) }
    val children: List<BrowserBookmark> = bookmarkTreeNode.children.map { BrowserBookmark(it) }
    val modifiable: Boolean = bookmarkTreeNode.unmodifiable?.let { false } ?: true
    val type: BookmarkType = BookmarkType.BOOKMARK
    // TODO: change to use CommonData

}