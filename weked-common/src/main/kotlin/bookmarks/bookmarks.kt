package bookmarks

import Listener
import SimpleListener
import kotlin.js.Promise

external class Bookmarks {

    val onChanged: Listener<(Int, BaseData) -> Unit>

    val onChildrenReordered: Listener<(Int, ReorderData) -> Unit>

    val onCreated: Listener<(Int, BookmarkTreeNode) -> Unit>

    val onRemoved: Listener<(Int, RemoveData) -> Unit>

    val onMoved: Listener<(Int, MoveData) -> Unit>

    val onImportBegan: SimpleListener

    val onImportEnded: SimpleListener

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