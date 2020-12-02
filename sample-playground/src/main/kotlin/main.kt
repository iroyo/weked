import bookmarks.createBookmarkFolder
import bookmarks.getBookmarkChildren
import bookmarks.onBookmarkCreated
import kotlinx.browser.document
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.js.onClickFunction

fun main() {
    onBookmarkCreated.addListener { index, browserBookmark ->
        console.log("$index $browserBookmark")
    }
    document.body!!.append.div {
        button {
            +"Click ME"
            onClickFunction = { createNewBookmark() }
        }
    }
}

fun createNewBookmark() {
    createBookmarkFolder {
        title = "YES YES"
    }
}

fun getBookmarksFromId() {
    getBookmarkChildren("root________").then {
        console.log(it)
    }
}
