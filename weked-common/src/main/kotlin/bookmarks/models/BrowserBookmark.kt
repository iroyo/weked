package bookmarks.models

import bookmarks.BookmarkTreeNode
import bookmarks.models.BookmarkType.BOOKMARK
import bookmarks.models.BookmarkType.FOLDER
import kotlin.js.Date

data class BrowserBookmark(
    val id: String?,
    val parentId: String?,
    val index: Int?,
    val type: BookmarkType,
    val modifiable: Boolean,
    val dateAdded: Date?,
    val dateGroupModified: Date?,
    val children: List<BrowserBookmark>
) {

    constructor(bookmark: BookmarkTreeNode) : this(
        id = bookmark.id,
        index = bookmark.index,
        parentId = bookmark.parentId,
        dateAdded = bookmark.dateAdded?.let { Date(it) },
        dateGroupModified = bookmark.dateGroupModified?.let { Date(it) },
        modifiable = bookmark.unmodifiable?.let { false } ?: true,
        children = bookmark.children?.map(::BrowserBookmark) ?: emptyList(),
        type = bookmark.type?.let {
            BookmarkType.valueOf(it.toUpperCase())
        } ?: bookmark.url?.let { BOOKMARK } ?: FOLDER
    )

}