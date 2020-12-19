package examples

import data.removeData
import kotlin.js.Date

fun testingBrowsingDataSettings() {
}

fun testingRemoveDataFromDayAgo() {
    val dayAgo = Date().getTime() - (1000 * 60 * 60 * 24)

    removeData {
        from(dayAgo)
        from {
            cacheApp()
        }
    }.then {
        console.log("Completed")
    }
}