import containers.onContainerUpdated
import containers.queryContainers
import containers.updateContainer
import kotlinx.html.dom.append
import kotlinx.html.li
import kotlinx.html.ul
import org.w3c.dom.Node
import tabs.Tab

fun main() {
    onContainerUpdated.addListener {
        console.log(it)
    }

    queryContainers().then {
        updateContainer(it.last().cookieStoreId, name = "micoca").then {
            console.log("completed")
        }
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

