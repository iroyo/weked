package bookmarks

import kotlin.js.Promise

external class Bookmarks {

    fun create(details: CommonData): Promise<BookmarkTreeNode>

    fun update(id: String, details: BaseData): Promise<BookmarkTreeNode>

    fun move(id: String, destination: Indexable): Promise<BookmarkTreeNode>

    fun getTree(): Promise<Array<BookmarkTreeNode>>

    fun getSubTree(id: String): Promise<Array<BookmarkTreeNode>>

    fun get(ids: Array<String>): Promise<Array<BookmarkTreeNode>>

    fun getChildren(id: String): Promise<Array<BookmarkTreeNode>>

    fun getRecent(numberOfItems: Int): Promise<Array<BookmarkTreeNode>>

    fun search(query: String): Promise<Array<BookmarkTreeNode>>

    fun search(query: QueryData): Promise<Array<BookmarkTreeNode>>

    fun remove(id: String): Promise<Unit>

    fun removeTree(id: String): Promise<Unit>

}