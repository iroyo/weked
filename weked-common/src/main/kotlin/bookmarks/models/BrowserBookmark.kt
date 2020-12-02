package bookmarks.models

import bookmarks.BookmarkTreeNode
import bookmarks.CreateBookmarkDetails
import kotlin.js.Date

class BrowserBookmark(
    bookmark: BookmarkTreeNode
) : CreateBookmarkDetails by bookmark {

    val id: String? = bookmark.id

    val modifiable: Boolean = bookmark.unmodifiable?.let { false } ?: true
    val dateAdded: Date? = bookmark.dateAdded?.let { Date(it) }
    val dateGroupModified: Date? = bookmark.dateGroupModified?.let { Date(it) }
    val children: List<BrowserBookmark> = bookmark.children?.map(::BrowserBookmark) ?: emptyList()
    val type: BookmarkType = bookmark.type?.let {
        BookmarkType.valueOf(it.toUpperCase())
    } ?: bookmark.url?.let {
        BookmarkType.BOOKMARK
    } ?: BookmarkType.FOLDER

}