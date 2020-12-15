package data

import browser
import isFirefox
import jsObject

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

private fun createRemovalOptions(
    time: Double = 0.0,
    originTypes: OriginTypes,
    cookieId: String? = null,
) = jsObject<RemovalOptions>().apply {
    this.since = time
    if (isFirefox) {
        cookieId?.let { this.cookieStoreId = it }
        //this.hostnames = data.hostNames?.toTypedArray()
    } else {
        //this.origins = data.includingOrigins?.toTypedArray()
        //this.excludeOrigins = data.excludingOrigins?.toTypedArray()
        this.originTypes = originTypes
    }
}

fun removeDataFromAllOrigins(timeInMillis: Double, block: DataTypeSet.() -> Unit) =
    removeData(timeInMillis, fromNormalWebs = true, fromInstalledWebs = true, fromExtensions = true, block)


// Crear un removeData que accepti una lambda per acabar el "chain" i un altra funció overloaded per aplicar una continuació que a la vegada estara overloaded amb una lambda per acabar i una per seguir
fun removeData(
    timeInMillis: Double,
    fromNormalWebs: Boolean = true,
    fromInstalledWebs: Boolean = false,
    fromExtensions: Boolean = false,
    block: DataTypeSet.() -> Unit
) = api.remove(createRemovalOptions(timeInMillis, createOriginType(fromExtensions, fromNormalWebs, fromInstalledWebs)), jsObject<DataTypeSet>().apply(block))