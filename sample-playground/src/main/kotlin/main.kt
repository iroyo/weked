import kotlinx.browser.document
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.js.onClickFunction

fun main() {
    browser.alarms.onAlarm.addListener {
        console.log(it)
    }
    document.body!!.append.div {
        button {
            +"Click ME"
            onClickFunction = { }
        }
    }
}

