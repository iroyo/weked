package alarm

import browser

private val api = browser.alarms

val onAlarmTriggered = api.onAlarm

val getAllAlarms get() = api.getAll()

val removeAllAlarms get() = api.clearAll()

fun removeAlarm(name: String) = api.clear(name)

fun getAlarm(name: String) = api.get(name)


