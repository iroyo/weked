import kotlinx.browser.document
import kotlinx.html.dom.append
import kotlinx.html.li
import kotlinx.html.ul
import org.w3c.dom.Node
import tabs.Tab
import tabs.queryTabs

fun main() {
    queryTabs {
        index = 0
    }.then {
        document.body?.listTabs(it)
    }
}

fun Node.listTabs(tabs: Array<Tab>) {
    append {
        ul {
            tabs.forEach {
                console.log(it.mutedInfo)
                li {
                    +(it.title ?: "Unknown title")
                }
            }
        }
    }
}

