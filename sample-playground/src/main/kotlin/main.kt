import examples.testAllCookies
import kotlinx.browser.document
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.js.onClickFunction

fun main() {
    document.body!!.append.div {
        console.log("hey hey")

        testAllCookies()
        button {
            +"Click ME"
            onClickFunction = {}
        }
    }
}



