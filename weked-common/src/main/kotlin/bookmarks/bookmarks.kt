package bookmarks

import kotlin.js.Promise

external class Bookmarks {

    fun get(id: String): Promise<Array<BookmarkTreeNode>>

    fun search(query: String): Promise<Array<BookmarkTreeNode>>

}