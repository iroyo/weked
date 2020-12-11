package examples

import data.models.OriginType

import data.removeData
import data.removeHistory
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
        it
            .from(Date().getTime())
            .fromOriginsExcluding()
    }) {

    }


    removeHistory(0.0, OriginType.INSTALLED_WEBS, "google.com", "facebook.com").then {
        console.log("Completed")
    }
}