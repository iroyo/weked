package data

import browser
import isFirefox
import jsObject
import kotlin.js.Date

private val api = browser.browsingData

private fun createOriginType(
    originFromExtensions: Boolean?,
    originFromNormalWebs: Boolean?,
    originFromHostedWebs: Boolean?
) = jsObject<OriginTypes>().apply {
    this.extension = originFromExtensions
    this.protectedWeb = originFromHostedWebs
    this.unprotectedWeb = originFromNormalWebs
}

val RemoveConfiguration.fromAllOrigins
    get() = from(
        hostedWebs = true,
        normalWebs = true,
        extensions = true
    )

fun RemoveConfiguration.from(
    normalWebs: Boolean,
    installedWebs: Boolean = false,
    extensions: Boolean = false,
) = this.apply {
    if (!isFirefox) options.originTypes = createOriginType(extensions, normalWebs, installedWebs)
}

private fun removeDataFromDate(millis: Int) = removeData(Date().getTime() - millis)

private const val HOUR_IN_MILLIS = 1000 * 60 * 60
private const val DAY_IN_MILLIS = HOUR_IN_MILLIS * 24
private const val WEEK_IN_MILLIS = DAY_IN_MILLIS * 7

val removeDataFromLastHour get() = removeDataFromDate(HOUR_IN_MILLIS)
val removeDataFromLastDay get() = removeDataFromDate(DAY_IN_MILLIS)
val removeDataFromLastWeek get() = removeDataFromDate(WEEK_IN_MILLIS)
val removeDataFromAllTime get() = removeData(0.0)

private fun buildRemoveAction(configuration: RemoveConfiguration, block: DataTypeSet.() -> Unit) = with(configuration) {
    api.remove(options, targets.apply(block))
}

fun removeDataFromLastHour(block: DataTypeSet.() -> Unit) = buildRemoveAction(removeDataFromLastHour, block)
fun removeDataFromLastDay(block: DataTypeSet.() -> Unit) = buildRemoveAction(removeDataFromLastDay, block)
fun removeDataFromLastWeek(block: DataTypeSet.() -> Unit) = buildRemoveAction(removeDataFromLastWeek, block)
fun removeDataFromAllTime(block: DataTypeSet.() -> Unit) = buildRemoveAction(removeDataFromAllTime, block)

fun removeData(timeInMillis: Double, block: DataTypeSet.() -> Unit) = buildRemoveAction(removeData(timeInMillis), block)

fun removeData(timeInMillis: Double): RemoveConfiguration = RemoveConfiguration().apply {
    options.since = timeInMillis
}

open class BaseConfiguration internal constructor(
    internal val options: RemovalOptions = jsObject()
) {

    val fromAllOrigins = from(hostedWebs = true, normalWebs = true, extensions = true)

    fun from(normalWebs: Boolean, hostedWebs: Boolean = false, extensions: Boolean = false) = this.apply {
        if (!isFirefox) options.originTypes = createOriginType(extensions, normalWebs, hostedWebs)
    }

}

class RemoveConfiguration internal constructor(
    internal val targets: DataTypeSet = jsObject()
) : BaseConfiguration() {




}

// removeDataFor {



/*

/**
 * Clears the browser's cache.
 */
fun clearAllCache() = api.removeCache(jsObject())

/**
 * Removes cookies.
 */
fun removeCookies(cookieId: String? = null, options: (AllConfigurations) -> ConfigurationData) =
    api.removeCookies(convertOptions(options(RemovalConfiguration()).data, cookieId))

/**
 * Clears the IndexedDB's history.
 */
fun removeIndexedDB(cookieId: String? = null, options: (AllConfigurations) -> ConfigurationData) =
    api.removeHistory(convertOptions(options(RemovalConfiguration()).data, cookieId))

/**
 * Clears the browser's download history. Note that this does not delete the downloaded objects themselves, only records of downloads in the browser's history.
 */
fun removeDownloads(options: (AllConfigurations) -> ConfigurationData) =
    api.removeDownloads(convertOptions(options(RemovalConfiguration()).data))

/**
 * Clears saved form data.
 */
fun removeFormData(options: (AllConfigurations) -> ConfigurationData) =
    api.removeFormData(convertOptions(options(RemovalConfiguration()).data))

/**
 * Clears the browser's history.
 */
fun removeHistory(options: (AllConfigurations) -> ConfigurationData) =
    api.removeHistory(convertOptions(options(RemovalConfiguration()).data))

/**
 * Clears any local storage created by websites.
 */
fun removeLocalStorage(options: (AllConfigurations) -> ConfigurationData) =
    api.removeLocalStorage(convertOptions(options(RemovalConfiguration()).data))

/**
 *  Clears saved passwords.
 */
fun removePasswords(options: (AllConfigurations) -> ConfigurationData) =
    api.removePasswords(convertOptions(options(RemovalConfiguration()).data))

/**
 * Clears data associated with plugins.
 */
fun removePluginData(options: (AllConfigurations) -> ConfigurationData) =
    api.removePluginData(convertOptions(options(RemovalConfiguration()).data))

fun settingsData() = api.settings()
 */
