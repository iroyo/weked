package bookmarks

import kotlin.js.Promise

external class Bookmarks {

    fun update(details: BaseData): Promise<BookmarkTreeNode>

    fun create(details: CommonData): Promise<BookmarkTreeNode>
    
    fun move(id: String, destination: Indexable): Promise<BookmarkTreeNode>

    fun getTree(): Promise<Array<BookmarkTreeNode>>

    fun get(id: String): Promise<Array<BookmarkTreeNode>>

    fun getChildren(id: String): Promise<Array<BookmarkTreeNode>>

    fun getSubTree(id: String): Promise<Array<BookmarkTreeNode>>

    fun getRecent(numberOfItems: Int): Promise<Array<BookmarkTreeNode>>

    fun search(query: String): Promise<Array<BookmarkTreeNode>>

    fun remove(id: String): Promise<Unit>

    fun removeTree(id: String): Promise<Unit>

}