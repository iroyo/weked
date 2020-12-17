package examples

import data.removeDataFrom
import kotlin.js.Date

fun testingBrowsingDataSettings() {
}

fun testingRemoveDataFromDayAgo() {
    val dayAgo = Date().getTime() - (1000 * 60 * 60 * 24)

    removeDataFrom { cache() }
}