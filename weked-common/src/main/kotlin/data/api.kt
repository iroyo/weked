package data

import browser
import create
import isChrome
import isFirefox
import jsObject
import kotlin.js.Date

private val api = browser.browsingData

typealias Provider<T> = () -> T

private const val HOUR_IN_MILLIS = 1000 * 60 * 60
private const val DAY_IN_MILLIS = HOUR_IN_MILLIS * 24
private const val WEEK_IN_MILLIS = DAY_IN_MILLIS * 7

sealed class OriginsList(val data: Array<String>) {
    class Including(data: Array<String>) : OriginsList(data)
    class Excluding(data: Array<String>) : OriginsList(data)
}



private fun createDataTypes(from: TargetConfiguration.() -> Unit) = TargetConfiguration().apply(from).targets

private fun createRemovalOptions(
    getTime: Provider<Double>,
    getOrigins: Provider<OriginTypes>? = null,
    getOriginsList: Provider<OriginsList>? = null,
) = create<RemovalOptions> {
    since = getTime()
    getOrigins?.takeIf { isChrome }?.let { this.originTypes = it() }
    getOriginsList?.let { it() }?.takeIf { isChrome && it.data.isNotEmpty() }?.let { result ->
        when (result) {
            is OriginsList.Including -> this.origins = result.data
            is OriginsList.Excluding -> this.excludeOrigins = result.data
        }
    }
}

val fromAllTime: () -> Double = { 0.0 }
val fromLastHour = fromDate(HOUR_IN_MILLIS)
val fromLastDay = fromDate(DAY_IN_MILLIS)
val fromLastWeek = fromDate(WEEK_IN_MILLIS)
fun fromDate(timeInMillis: Int): Provider<Double> = { Date().getTime() - timeInMillis }

/**
 * An object whose properties specify which origin types ought to be cleared
 * * Please ensure that you really want to remove application data before adding 'hostedWebs' or 'extensions'.
 * @param extensions Extensions and packaged applications a user has installed (be REALLY careful!)
 * @param hostedWebs Websites that have been installed as hosted applications (be careful!)
 */
fun from(normalWebs: Boolean, hostedWebs: Boolean = false, extensions: Boolean = false): () -> OriginTypes = {
    jsObject<OriginTypes>().apply {
        this.extension = extensions
        this.protectedWeb = hostedWebs
        this.unprotectedWeb = normalWebs
    }
}

val fromAllOrigins: () -> OriginTypes = from(normalWebs = true, hostedWebs = true, extensions = true)

/**
 * When present, only data for origins in this list is deleted.
 * * Only supported for cookies, storage and cache.
 * * Cookies are cleared for the whole registrable domain.
 */
fun fromOriginsIncluding(vararg value: String): Provider<OriginsList> = {
    OriginsList.Including(arrayOf(*value))
}

/**
 * When present, data for origins in this list is excluded from deletion.
 * * Can't be used together with origins.
 * * Only supported for cookies, storage and cache.
 */
fun fromOriginsExcluding(vararg value: String): Provider<OriginsList> = {
    OriginsList.Excluding(arrayOf(*value))
}

/**
 * You must pass in just a hostname here, without protocol
 */
fun fromHostNames(vararg value: String): Provider<Array<String>> = {
    arrayOf(*value)
}

/**
 * Clears various types of browsing data stored in a user's profile.
 */
fun removeData(
    timeProvider: () -> Double,
    originsProvider: () -> OriginTypes = from(true),
    targets: TargetConfiguration.() -> Unit
) = api.remove(
    createRemovalOptions(timeProvider, originsProvider),
    createDataTypes(targets)
)

/**
 * Clears various types of browsing data stored in a user's profile.
 */
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
    timeProvider: Provider<Double>,
    originsProvider: Provider<OriginTypes>? = null,
    originsListProvider: Provider<OriginsList>? = null,
) = api.removeCache(createRemovalOptions(timeProvider, originsProvider, originsListProvider))

/**
 * Removes cookies.
 */
fun removeCookies(
    timeProvider: Provider<Double>,
    originsProvider: Provider<OriginTypes>? = null,
    originsListProvider: Provider<OriginsList>? = null,
    hostNamesProvider: Provider<Array<String>>? = null,
    cookieId: String? = null,
) = api.removeCookies(createRemovalOptions(timeProvider, originsProvider, originsListProvider).apply {
    if (isFirefox) {
        this.cookieStoreId = cookieId
        hostNamesProvider?.let { this.hostnames = it() }
    }
})

/**
 * Clears any local storage created by websites.
 */
fun removeLocalStorage(
    timeProvider: Provider<Double>,
    originsProvider: Provider<OriginTypes>? = null,
    originsListProvider: Provider<OriginsList>? = null,
    hostNamesProvider: Provider<Array<String>>? = null,
) = api.removeLocalStorage(createRemovalOptions(timeProvider, originsProvider, originsListProvider).apply {
    hostNamesProvider?.takeIf { isFirefox }?.let { this.hostnames = it() }
})

/**
 * Clears the IndexedDB's history.
 */
fun removeIndexedDB(
    timeProvider: Provider<Double>,
    originsProvider: Provider<OriginTypes>? = null,
    originsListProvider: Provider<OriginsList>? = null,
    cookieId: String? = null,
) = api.removeIndexedDB(createRemovalOptions(timeProvider, originsProvider, originsListProvider).apply {
    if (isFirefox) this.cookieStoreId = cookieId
})


/**
 * Clears the browser's download history. Note that this does not delete the downloaded objects themselves, only records of downloads in the browser's history.
 */
fun removeDownloads(
    timeProvider: Provider<Double>,
    originsProvider: Provider<OriginTypes>? = null,
) = api.removeDownloads(createRemovalOptions(timeProvider, originsProvider))

/**
 * Clears saved form data.
 */
fun removeFormData(
    timeProvider: Provider<Double>,
    originsProvider: Provider<OriginTypes>? = null,
) = api.removeFormData(createRemovalOptions(timeProvider, originsProvider))

/**
 * Clears the browser's history.
 */
fun removeHistory(
    timeProvider: Provider<Double>,
    originsProvider: Provider<OriginTypes>? = null,
) = api.removeHistory(createRemovalOptions(timeProvider, originsProvider))

/**
 *  Clears saved passwords.
 */
fun removePasswords(
    timeProvider: Provider<Double>,
    originsProvider: Provider<OriginTypes>? = null,
) = api.removePasswords(createRemovalOptions(timeProvider, originsProvider))

/**
 * Clears data associated with plugins.
 */
fun removePluginData(
    timeProvider: Provider<Double>,
    originsProvider: Provider<OriginTypes>? = null,
) = api.removePluginData(createRemovalOptions(timeProvider, originsProvider))

/**
 * Reports which types of data are currently selected in the 'Clear browsing data' settings UI
 */
fun settingsData() = api.settings()

