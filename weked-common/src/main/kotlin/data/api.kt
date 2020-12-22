package data

import browser
import create
import isChrome
import jsObject
import kotlin.js.Date

private val api = browser.browsingData

typealias Provider<T> = () -> T

private const val HOUR_IN_MILLIS = 1000 * 60 * 60
private const val DAY_IN_MILLIS = HOUR_IN_MILLIS * 24
private const val WEEK_IN_MILLIS = DAY_IN_MILLIS * 7

sealed class OriginsList(val data: Array<String>) {
    class Including(data: Array<String>): OriginsList(data)
    class Excluding(data: Array<String>): OriginsList(data)
}

private fun timeProviderFromDate(timeInMillis: Int): () -> Double = { Date().getTime() - timeInMillis }

private fun createDataTypes(from: TargetConfiguration.() -> Unit) = TargetConfiguration().apply(from).targets

private fun createRemovalOptions(
    getTime: Provider<Double>,
    getOrigins: Provider<OriginTypes>? = null,
    getOriginsList: Provider<OriginsList>? = null,
) = create<RemovalOptions> {
    since = getTime()
    getOrigins?.takeIf { isChrome }?.let { this.originTypes = it() }
    getOriginsList?.let { it() }?.takeIf { isChrome && it.data.isNotEmpty() }?.let { result ->
        when(result) {
            is OriginsList.Including -> this.origins = result.data
            is OriginsList.Excluding -> this.excludeOrigins = result.data
        }
    }
}

val fromAllTime: () -> Double = { 0.0 }
val fromLastHour = timeProviderFromDate(HOUR_IN_MILLIS)
val fromLastDay = timeProviderFromDate(DAY_IN_MILLIS)
val fromLastWeek = timeProviderFromDate(WEEK_IN_MILLIS)

fun from(normalWebs: Boolean, hostedWebs: Boolean = false, extensions: Boolean = false): () -> OriginTypes = {
    jsObject<OriginTypes>().apply {
        this.extension = extensions
        this.protectedWeb = hostedWebs
        this.unprotectedWeb = normalWebs
    }
}

val fromAllOrigins: () -> OriginTypes = from(normalWebs = true, hostedWebs = true, extensions = true)

fun fromOriginsIncluding(vararg value: String): Provider<OriginsList> = {
    OriginsList.Including(arrayOf(*value))
}

fun fromOriginsExcluding(vararg value: String): Provider<OriginsList> = {
    OriginsList.Excluding(arrayOf(*value))
}


fun removeData(
    timeProvider: () -> Double,
    originsProvider: () -> OriginTypes = from(true),
    targets: TargetConfiguration.() -> Unit
) = api.remove(
    createRemovalOptions(timeProvider, originsProvider),
    createDataTypes(targets)
)

fun removeData(
    timeProvider: () -> Double,
    originsListProvider: Provider<OriginsList>?,
    originsTypeProvider: Provider<OriginTypes>? = null,
    targets: SpecificOriginTargetConfiguration.() -> Unit
) = api.remove(
    createRemovalOptions(timeProvider, originsTypeProvider, originsListProvider),
    createDataTypes(targets)
)

/**
 * Clears the browser's cache.
 */
fun removeCache(
    timeProvider: () -> Double,
    originsProvider: () -> OriginTypes = from(true),
) = api.removeCache(createRemovalOptions(timeProvider, originsProvider))


/*
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
