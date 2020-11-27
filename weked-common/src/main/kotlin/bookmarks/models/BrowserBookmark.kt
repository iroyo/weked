package bookmarks.models

import kotlin.js.Date

class BrowserBookmark(
    val index: Int?,
    val id: String?,
    val parentId: String?,
    val url: String?,
    val title: String,
    val dateAdded: Date?,
    val dateGroupModified: Date?,
    val children: List<BrowserBookmark>,
    val modifiable: Boolean = true,
    val type: BookmarkType = BookmarkType.BOOKMARK,
) {

}