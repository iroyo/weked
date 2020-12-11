package data

import browser
import jsObject

private val api = browser.browsingData

private fun createOriginType(value: OriginType) = jsObject<OriginTypes>().apply {
    this.extension = value == OriginType.EXTENSIONS
    this.protectedWeb = value == OriginType.INSTALLED_WEBS
    this.unprotectedWeb = value == OriginType.NORMAL_WEBS
}

private fun createRemovalOptions(since: Double, originType: OriginType?, vararg host: String) = jsObject<RemovalOptions>().apply {
    this.since = since
    this.hostnames = arrayOf(*host)
    originType?.let { this.originTypes = createOriginType(it) }
}

fun removeData(since: Double = 0.0, originType: OriginType? = null, vararg host: String, block: CommonDataTypeSet.() -> Unit) =
    api.remove(createRemovalOptions(since, originType, *host), jsObject<DataTypeSet>().apply(block))

fun removeData(cookieId: String?, since: Double = 0.0, originType: OriginType? = null, vararg host: String, block: CookieBasedDataTypeSet.() -> Unit) =
    api.remove(createRemovalOptions(since, originType, *host).apply {
        cookieId?.let { this.cookieStoreId = it }
    }, jsObject<DataTypeSet>().apply(block))

/**
 * Clears the browser's cache.
 */
fun clearAllCache() = api.removeCache(jsObject())

/**
 * Removes cookies.
 */
fun removeCookies(since: Double = 0.0, originType: OriginType? = null, vararg host: String) =
    api.removeCookies(createRemovalOptions(since, originType, *host))

/**
 * Clears the browser's download history. Note that this does not delete the downloaded objects themselves, only records of downloads in the browser's history.
 */
fun removeDownloads(since: Double = 0.0, originType: OriginType? = null, vararg host: String) =
    api.removeDownloads(createRemovalOptions(since, originType, *host))


/**
 * Clears saved form data.
 */
fun removeFormData(since: Double = 0.0, originType: OriginType? = null, vararg host: String) =
    api.removeFormData(createRemovalOptions(since, originType, *host))

/**
 * Clears the browser's history.
 */
fun removeHistory(since: Double = 0.0, originType: OriginType? = null, vararg host: String) =
    api.removeHistory(createRemovalOptions(since, originType, *host))

/**
 * Clears any local storage created by websites.
 */
fun removeLocalStorage(since: Double = 0.0, originType: OriginType? = null, vararg host: String) =
    api.removeLocalStorage(createRemovalOptions(since, originType, *host))

/**
 *  Clears saved passwords.
 */
fun removePasswords(since: Double = 0.0, originType: OriginType? = null, vararg host: String) =
    api.removePasswords(createRemovalOptions(since, originType, *host))

/**
 * Clears data associated with plugins.
 */
fun removePluginData(since: Double = 0.0, originType: OriginType? = null, vararg host: String) =
    api.removePluginData(createRemovalOptions(since, originType, *host))

