import kotlinx.html.dom.append
import kotlinx.html.li
import kotlinx.html.ul
import org.w3c.dom.Node
import tabs.models.Tab

fun main() {

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

