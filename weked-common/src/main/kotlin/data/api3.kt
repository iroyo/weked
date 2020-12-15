package data

import browser
import isFirefox
import jsObject
import kotlin.js.Date

fun main() {


}

private val api = browser.browsingData

private fun createOriginType(
    originFromExtensions: Boolean?,
    originFromNormalWebs: Boolean?,
    originFromInstalledWebs: Boolean?
) = jsObject<OriginTypes>().apply {
    this.extension = originFromExtensions
    this.protectedWeb = originFromInstalledWebs
    this.unprotectedWeb = originFromNormalWebs
}

val (() -> RemovalOptions).fromAllOrigins get() = from(
    installedWebs = true,
    normalWebs = true,
    extensions = true
)

fun (() -> RemovalOptions).from(
    normalWebs: Boolean = true,
    installedWebs: Boolean = false,
    extensions: Boolean = false,
): () -> RemovalOptions = {
    this().apply {
        if (!isFirefox) originTypes = createOriginType(extensions, normalWebs, installedWebs)
    }
}

private fun removeDataFromDate(millis: Int) = removeData(Date().getTime() - millis)

private fun configureDataType(block: DataTypeSet.() -> Unit) = jsObject<DataTypeSet>().apply(block)

private const val HOUR_IN_MILLIS = 1000 * 60 * 60
private const val DAY_IN_MILLIS = HOUR_IN_MILLIS * 24
private const val WEEK_IN_MILLIS = DAY_IN_MILLIS * 7

val removeDataFromLastHour get() = removeDataFromDate(HOUR_IN_MILLIS)
val removeDataFromLastDay get() = removeDataFromDate(DAY_IN_MILLIS)
val removeDataFromLastWeek get() = removeDataFromDate(WEEK_IN_MILLIS)
val removeDataFromAllTime get() = removeData(0.0)

fun removeDataFromLastHour(block: DataTypeSet.() -> Unit) = api.remove(removeDataFromLastHour(), configureDataType(block))
fun removeDataFromLastDay(block: DataTypeSet.() -> Unit) = api.remove(removeDataFromLastDay(), configureDataType(block))
fun removeDataFromLastWeek(block: DataTypeSet.() -> Unit) = api.remove(removeDataFromLastWeek(), configureDataType(block))
fun removeDataFromAllTime(block: DataTypeSet.() -> Unit) = api.remove(removeDataFromAllTime(), configureDataType(block))

fun removeData(timeInMillis: Double, block: DataTypeSet.() -> Unit) =
    api.remove(removeData(timeInMillis)(), jsObject<DataTypeSet>().apply(block))

fun removeData(timeInMillis: Double) : () -> RemovalOptions = {
    jsObject<RemovalOptions>().apply {
        since = timeInMillis
    }
}