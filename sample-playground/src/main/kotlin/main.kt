import examples.testingOriginTypes
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.js.onClickFunction

fun main() {
    console.log(window.navigator.userAgent)
    document.body!!.append.div {
        button {
            +"Click ME"
            onClickFunction = { testingOriginTypes() }
        }
    }
}



