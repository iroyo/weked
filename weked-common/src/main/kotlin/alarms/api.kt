package alarms

import browser
import jsObject

private val api = browser.alarms

/**
 * Fired when any alarm set by the extension goes off.
 */
val onAlarmTriggered = api.onAlarm

/**
 * Gets all active alarms for the extension.
 */
val getAllAlarms get() = api.getAll()

/**
 * Cancels all active alarms.
 */
val removeAllAlarms get() = api.clearAll()

/**
 * Cancels an alarm, given its name
 */
fun removeAlarm(name: String) = api.clear(name)

/**
 * Gets an alarm, given its name.
 */
fun getAlarm(name: String) = api.get(name)

private fun createAlarmData(period: Float?) = jsObject<AlarmData>().apply {
    period?.let { this.periodInMinutes = it }
}

fun ((AlarmData) -> Unit).ringAt(timeInMillis: Float, repeatAfter: Float? = null) =
    this(createAlarmData(repeatAfter).apply {
        this.`when` = timeInMillis
    })

fun ((AlarmData) -> Unit).ringAfter(delayInMinutes: Float, repeatAfter: Float? = null) =
    this(createAlarmData(repeatAfter).apply {
        this.delayInMinutes = delayInMinutes
    })


/**
 * Creates a new alarm for the current browser session.
 * * An alarm may fire once or multiple times.
 * * An alarm is cleared after it fires for the last time
 */
fun createAlarm(name: String? = null): (AlarmData) -> Unit = { alarm ->
    api.create(name, alarm)
}
