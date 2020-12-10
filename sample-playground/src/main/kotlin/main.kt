import examples.testBrowserSettings2
import kotlinx.browser.document
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.js.onClickFunction
import settings.onSettingChanged

fun main() {
    onSettingChanged { cacheEnabled }.addListener {
        console.log(it)
    }
    document.body!!.append.div {
        button {
            +"Click ME"
            onClickFunction = { testBrowserSettings2() }
        }
    }
}

