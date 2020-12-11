package examples

import data.removeData
import kotlin.js.Date

fun deleteHistoryFromAWeekAgo() {
    val week = 1000 * 60 * 60 * 24 * 7
    removeData(since = Date().getTime() - week) {
        history = true
    }.then {
        console.log("finished")
    }
}

fun testingOriginTypes() {

    removeData({
        it.whenHostNameMatches()
    }) {

    }


}