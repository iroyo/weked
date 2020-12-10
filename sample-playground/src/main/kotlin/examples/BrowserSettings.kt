package examples

import settings.get
import settings.set


fun testBrowserSettings1() {
    get { cacheEnabled }.then { console.log(it) }
}

fun testBrowserSettings2(){
    get { cacheEnabled }.then {
        set(!it.value) {
            cacheEnabled
        }.then {
            console.log("changed")
        }
    }

}