package examples

import data.removeData
import data.settingsData
import kotlin.js.Date

fun testingBrowsingDataSettings() {
    settingsData().then {
        console.log(it)
    }
}

fun testingRemoveDataFromDayAgo() {
    val dayAgo = Date().getTime() - (1000 * 60 * 60 * 24)
    removeData({ it.from(dayAgo).whenHostNameMatches("google.com").fromAllOrigins() }) {
        cookies = true
        history = true
    }.then {
        console.log("Completed")
    }

}