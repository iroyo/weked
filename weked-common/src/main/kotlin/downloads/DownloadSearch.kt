package downloads

import downloads.models.DangerType
import downloads.models.InterruptReason
import downloads.models.State
import jsObject
import kotlin.js.Date

class DownloadSearch(internal val data: DownloadQuery = jsObject()) : DownloadQueryCommon by data {

    private inline fun assign(block: DownloadQuery.() -> Unit) = with(data, block)

    fun startedBefore(timeInMillis: Double) = assign { startedBefore = timeInMillis }
    fun startedAfter(timeInMillis: Double) = assign { startedAfter = timeInMillis }
    fun endedBefore(timeInMillis: Double) = assign { endedBefore = timeInMillis }
    fun endedAfter(timeInMillis: Double) = assign { endedAfter = timeInMillis }

    fun startedBefore(date: Date) = assign { startedBefore = date }
    fun startedAfter(date: Date) = assign { startedAfter = date }
    fun endedBefore(date: Date) = assign { endedBefore = date }
    fun endedAfter(date: Date) = assign { endedAfter = date }

    fun state(value: State) = assign { state = value.name }
    fun state(value: DangerType) = assign { danger = value.name }
    fun error(value: InterruptReason) = assign { error = value.name }

}