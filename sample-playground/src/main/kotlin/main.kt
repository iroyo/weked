import kotlinx.browser.document
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.dom.append


fun main() {
    browser.windows.onCreated.addListener {
        console.log(it)
    }
    document.body!!.append.div {
        button {
            +"Click ME"
        }
    }
}