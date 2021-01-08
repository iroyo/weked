package history

import create
import kotlin.js.Date

class SearchConfigurator internal constructor(internal val data: SearchOptions = create {  }): SearchQuery by data {

    private inline fun assign(block: SearchOptions.() -> Unit) = with(data, block)

    fun startTime(timeInMillis: Double) = assign { startTime = timeInMillis}
    fun startTime(time: Date) = assign { startTime = time}
    fun endTime(timeInMillis: Double) = assign { endTime = timeInMillis}
    fun endTime(time: Date) = assign { endTime = time}
}