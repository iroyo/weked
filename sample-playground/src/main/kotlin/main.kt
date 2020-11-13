import kotlinx.browser.document
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.js.onClickFunction
import sidebar.setSidebarTitle

fun main() {
    document.body!!.append.div {
       button {
           +"Click ME"
           onClickFunction = {
               setSidebarTitle {
                   title = "Provas"
               }.then {
                   console.log(it)
               }.catch {
                   console.log(it)
               }
           }
       }
    }

}