package bookmarks

import bookmarks.models.BookmarkType
import bookmarks.models.BrowserBookmark
import browser
import jsObject

private val api = browser.bookmarks

private fun createBookmarkNode(type: BookmarkType, block: CommonData.() -> Unit) =
    api.create(jsObject<CommonData>()
        .apply(block)
        .apply {
            this.type = type.name.toLowerCase()
        }
    ).then(::BrowserBookmark)

fun createBookmark(data: CreateBookmarkDetails.() -> Unit = {}) = createBookmarkNode(BookmarkType.BOOKMARK, data)

fun createBookmarkFolder(data: CreateFolderDetails.() -> Unit = {}) = createBookmarkNode(BookmarkType.FOLDER, data)

fun createBookmarkDivider(data: CreateFolderDetails.() -> Unit = {}) = createBookmarkNode(BookmarkType.SEPARATOR, data)