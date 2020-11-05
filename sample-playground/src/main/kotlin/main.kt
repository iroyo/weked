import kotlinx.browser.document
import kotlinx.html.dom.append
import kotlinx.html.li
import kotlinx.html.ul
import org.w3c.dom.Node
import tabs.*
import tabs.models.ShareMode
import tabs.models.ShareMode.*
import window.models.WindowMode.NORMAL

fun main() {
    queryTabs {
        shareMode = Deactivated
    }.then {
        document.body?.listTabs(it)
    }
}

fun Node.listTabs(tabs: Array<Tab>) {
    append {
        ul {
            tabs.forEach {
                it.loading
                console.log(it.mutedInfo)
                li {
                    +(it.title ?: "Unknown title")
                }
            }
        }
    }
}

