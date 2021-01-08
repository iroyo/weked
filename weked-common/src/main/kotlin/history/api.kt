package history

import browser
import create
import history.models.BrowserVisitRecord
import history.models.TransitionType
import kotlin.js.Date

private val api = browser.history

/**
 * Removes all visits from the browser history.
 */
val deleteAllHistory get() = api.deleteAll()

/**
 * Removes all visits to pages that the user made during the given time range.
 */
fun deleteHistoryBetween(startDateInMillis: Float, endDateInMillis: Float) =
    api.deleteRange(create {
        this.startTime = startDateInMillis
        this.endTime = endDateInMillis
    })

/**
 * Removes all visits to the given URL from the browser history.
 */
fun deleteHistoryFor(url: String) = api.deleteUrl(create { this.url = url })

/**
 * Retrieves information about visits to a given page.
 */
fun getHistoryVisitsFor(url: String) = api.getVisits(create { this.url = url }).then { it.map(::BrowserVisitRecord) }

private fun createVisitRecord(url: String, title: String?, transition: TransitionType?, block: UrlOptions.() -> Unit) =
    api.addUrl(create(block).apply {
        this.url = url
        title?.let { this.title = it }
        transition?.let { this.transition = it.name.toLowerCase() }
    })

/**
 * Adds a record to the browser's history of a visit to the given URL.
 * * The visit's time is recorded as the time of the call (if null)
 * * The TransitionType is recorded as "link".
 */
fun addHistoryVisitFor(
    url: String,
    title: String? = null,
    visitTime: Long? = null,
    transition: TransitionType? = null,
) = createVisitRecord(url, title, transition) {
    visitTime?.let { this.visitTime = it }
}

/**
 * Adds a record to the browser's history of a visit to the given URL.
 * * The visit's time is recorded as the time of the call (if null)
 * * The TransitionType is recorded as "link".
 */
fun addHistoryVisitFor(
    url: String,
    title: String? = null,
    visitTime: Date? = null,
    transition: TransitionType? = null,
) = createVisitRecord(url, title, transition) {
    visitTime?.let { this.visitTime = it }
}

fun getHistory(block: SearchConfigurator.() -> Unit) = api.search(SearchConfigurator().apply(block).data)