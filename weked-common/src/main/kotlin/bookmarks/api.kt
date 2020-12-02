package bookmarks

import bookmarks.models.BrowserBookmark
import browser
import jsObject

private val api = browser.bookmarks

private fun createBookmarkNode(block: CommonData.() -> Unit) =
    api.create(jsObject<CommonData>().apply(block)).then(::BrowserBookmark)

fun createBookmark(block: CreateBookmarkDetails.() -> Unit = {}) = createBookmarkNode(block)

fun createBookmarkFolder(block: CreateFolderDetails.() -> Unit = {}) = createBookmarkNode(block)

private fun moveBookmarkNode(id: String, index: Int?, parentId: String?) = api.move(id, jsObject<Indexable>().apply {
    this.index = index
    this.parentId = parentId
}).then(::BrowserBookmark)

/**
 * Moves the specified BookmarkTreeNode to the provided location.
 */
fun moveBookmark(id: String, index: Int) = moveBookmarkNode(id = id, index = index, parentId = null)

/**
 * Moves the specified BookmarkTreeNode to the provided location.
 */
fun moveBookmark(id: String, parentId: String) = moveBookmarkNode(id = id, index = null, parentId = parentId)

/**
 * Moves the specified BookmarkTreeNode to the provided location.
 */
fun moveBookmark(id: String, index: Int, parentId: String) =
    moveBookmarkNode(id = id, index = index, parentId = parentId)

/**
 * Updates the properties of a bookmark or folder. Specify only the properties that you want to change; unspecified properties will be left unchanged.
 */
fun updateBookmark(id: String, url: String? = null, title: String? = null) = api.update(id, jsObject<BaseData>().apply {
    url?.let { this.url = it }
    title?.let { this.title = it }
}).then(::BrowserBookmark)

/**
 * Retrieves the entire Bookmarks hierarchy.
 */
val getAllBookmarks get() = api.getTree().then { it.map(::BrowserBookmark) }

/**
 * Retrieves the specified BookmarkTreeNode(s).
 * @throws InvalidGUID value for property 'guid'
 * * Value must either: be a string value, or have at least 1 items
 */
fun getBookmarks(vararg id: String) = api.get(arrayOf(*id)).then { it.map(::BrowserBookmark) }

/**
 * Retrieves part of the Bookmarks hierarchy, starting at the specified node.
 * returns an array containing a single object
 */
fun getBookmarkTree(id: String) = api.getSubTree(id).then { it.map(::BrowserBookmark) }

/**
 * Retrieves the children of the specified BookmarkTreeNode id.
 */
fun getBookmarkChildren(id: String) = api.getChildren(id).then { it.map(::BrowserBookmark) }

/**
 * Retrieves the recently added bookmarks.
 */
fun getRecentBookmarks(numberOfResults: Int = 5) = api.getRecent(numberOfResults).then { it.map(::BrowserBookmark) }

/**
 * Searches for BookmarkTreeNodes matching the given query.
 * Queries specified with an object produce BookmarkTreeNodes matching all specified properties
 */
fun searchBookmark(query: String) = api.search(query).then { it.map(::BrowserBookmark) }

/**
 * Searches for BookmarkTreeNodes matching the given query.
 * Queries specified with an object produce BookmarkTreeNodes matching all specified properties
 * If you pass an invalid URL, the function will throw an exception.
 */
fun searchBookmark(block: QueryData.() -> Unit) = api.search(jsObject<QueryData>().apply(block))
    .then { it.map(::BrowserBookmark) }

/**
 * Removes a bookmark or an empty bookmark folder.
 */
fun removeBookmarkWithId(id: String) = api.remove(id)

/**
 * Recursively removes a bookmark folder.
 */
fun removeBookmarkTreeWithId(id: String) = api.removeTree(id)

