package data

import browser
import data.TargetConfiguration.Companion.createDataTypes
import isChrome
import isFirefox
import jsObject
import kotlin.js.Date
import kotlin.js.Promise

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

private const val HOUR_IN_MILLIS = 1000 * 60 * 60
private const val DAY_IN_MILLIS = HOUR_IN_MILLIS * 24
private const val WEEK_IN_MILLIS = DAY_IN_MILLIS * 7

class RemoveConfigurator {

    private val options: RemovalOptions = jsObject()

    val fromAllOrigins = from(normalWebs = true, hostedWebs = true, extensions = true)
    val fromLastHour = from(Date().getTime() - HOUR_IN_MILLIS)
    val fromLastDay = from(Date().getTime() - DAY_IN_MILLIS)
    val fromLastWeek = from(Date().getTime() - WEEK_IN_MILLIS)
    val fromAllTime = from(0.0)

    fun from(timeInMillis: Double) = this.options.apply {
        since = timeInMillis
    }

    fun from(normalWebs: Boolean, hostedWebs: Boolean = false, extensions: Boolean = false) = this.options.apply {
        if (!isFirefox) originTypes = createOriginType(extensions, normalWebs, hostedWebs)
    }

    fun from(block: TargetConfiguration.() -> Unit): Promise<Unit> {
        val dataTypes = createDataTypes(block)
        console.log(options)
        console.log(dataTypes)
        return api.remove(options, dataTypes)
    }


}

fun removeData(block: RemoveConfigurator.() -> Promise<Unit>) = block(RemoveConfigurator())

/*
fun removeData(from: TargetConfiguration.() -> Unit, builder: RemovalConfiguration.() -> RemovalOptions) =
    api.remove(builder(RemovalConfiguration()), createDataTypes(from))

*/
class TargetConfiguration internal constructor() {
    internal val targets: DataTypeSet = jsObject()

    // @formatter:off
    fun cache() { targets.cache = true }
    fun cacheApp() { if (isChrome) targets.appcache = true }
    fun cacheStorage() { if (isChrome) targets.cacheStorage = true }
    fun cookies() { targets.cookies = true }
    fun downloads() { targets.downloads = true }
    fun formData() { targets.formData = true }
    fun fileSystems() { targets.fileSystems = true }
    fun history() { targets.history = true }
    fun webSQL() { if (isChrome) targets.webSQL = true }
    fun indexedDB() { targets.indexedDB = true }
    fun localStorage() { targets.localStorage = true }
    fun passwords() { targets.passwords = true }
    fun pluginData() { targets.pluginData = true }
    fun serviceWorkers() { targets.serviceWorkers = true }
    fun serverBoundCertificates() { targets.serverBoundCertificates = true }
    // @formatter:on

    companion object {
        internal fun createDataTypes(from: TargetConfiguration.() -> Unit) = TargetConfiguration().apply(from).targets
    }


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
