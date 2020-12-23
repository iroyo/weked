package examples

import data.fromAllTime
import data.fromLastDay
import data.fromOriginsIncluding
import data.removeData

fun testingBrowsingDataFromSpecificOrigins() {
    removeData(fromAllTime, fromOriginsIncluding("https://www.example.com")) {
        localStorage()
    }.then {
        console.log("Completed")
    }
}

fun testingRemoveDataFromDayAgo() {
    removeData(fromLastDay) {
        downloads()
        history()
    }.then {
        console.log("Completed")
    }
}