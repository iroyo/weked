package data

import browser
import data.models.*
import isFirefox
import jsObject

private val api = browser.browsingData

private fun createOriginType(value: OriginType) = jsObject<OriginTypes>().apply {
    this.extension = value == OriginType.EXTENSIONS
    this.protectedWeb = value == OriginType.INSTALLED_WEBS
    this.unprotectedWeb = value == OriginType.NORMAL_WEBS
}

private fun createOriginType(
    originFromExtensions: Boolean?,
    originFromNormalWebs: Boolean?,
    originFromInstalledWebs: Boolean?
) = jsObject<OriginTypes>().apply {
    this.extension = originFromExtensions
    this.protectedWeb = originFromInstalledWebs
    this.unprotectedWeb = originFromNormalWebs
}

/**
 * Removes the specified browsing data.
 */
fun removeData(options: (AllConfigurations) -> ConfigurationData, block: DataTypeSet.() -> Unit) =
    api.remove(convertOptions(options(RemovalConfiguration()).data), jsObject<DataTypeSet>().apply(block))

private fun convertOptions(data: Options, cookieId: String? = null) = jsObject<RemovalOptions>().apply {
    this.since = data.time
    if (isFirefox) {
        cookieId?.let { this.cookieStoreId = it }
        this.hostnames = data.hostNames?.toTypedArray()
    } else {
        this.origins = data.includingOrigins?.toTypedArray()
        this.excludeOrigins = data.excludingOrigins?.toTypedArray()
        this.originTypes = createOriginType(data.originFromExtensions, data.originFromNormalWebs, data.originFromInstalledWebs)
    }
}

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

