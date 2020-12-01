package bookmarks

import browser
import jsObject

private val api = browser.bookmarks

private fun createBookmarkNode(block: CommonData.() -> Unit) = api.create(jsObject<CommonData>().apply(block))

fun createBookmark(block: CreateBookmarkDetails.() -> Unit = {}) = createBookmarkNode(block)

fun createBookmarkFolder(block: CreateFolderDetails.() -> Unit = {}) = createBookmarkNode(block)

fun createBookmarkDivider(block: CreateFolderDetails.() -> Unit = {}) = createBookmarkNode(block)