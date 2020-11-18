import kotlinx.browser.document
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.js.onClickFunction
import storage.onDataChanged
import storage.onLocalStorage
import storage.saveData

fun main() {
    document.body!!.append.div {
        onDataChanged.addListener { update ->
            console.log(update.changes["is_logged"])
        }
        button {
            +"Click ME"
            onClickFunction = {
                saveData("is_logged" to false).onLocalStorage.then {
                    console.log("saved")
                }
            }
        }
    }

}