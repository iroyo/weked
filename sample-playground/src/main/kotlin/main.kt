import containers.queryContainers
import kotlinx.html.dom.append
import kotlinx.html.li
import kotlinx.html.ul
import org.w3c.dom.Node
import tabs.Tab

fun main() {
    queryContainers().then {
        console.log(it.toString())
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

