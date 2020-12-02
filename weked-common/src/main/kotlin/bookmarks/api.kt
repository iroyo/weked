package bookmarks

import bookmarks.models.BrowserBookmark
import browser
import jsObject

private val api = browser.bookmarks

private fun createBookmarkNode(block: CommonData.() -> Unit) = api.create(jsObject<CommonData>().apply(block)).then(::BrowserBookmark)

fun createBookmark(block: CreateBookmarkDetails.() -> Unit = {}) = createBookmarkNode(block)

fun createBookmarkFolder(block: CreateFolderDetails.() -> Unit = {}) = createBookmarkNode(block)