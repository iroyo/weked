import kotlinx.browser.document
import kotlinx.html.dom.append
import kotlinx.html.js.p
import kotlinx.html.li
import kotlinx.html.ul
import org.w3c.dom.Node
import tabs.*
import tabs.models.Tab

fun main() {
    queryTabs {
       loading = true
    }.then {
        if (it.isNotEmpty()) {
            document.body?.listTabs(it)
        } else {
            document.body?.append {
                p { +"No result matched the query" }
            }
        }
    }
}

fun Node.listTabs(tabs: Array<Tab>) {
    append {
        ul {
            tabs.forEach {
                li {
                    +(it.title ?: "Unknown title")
                }
            }
        }
    }
}

