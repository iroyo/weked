package data

import kotlin.js.Promise

external class BrowserData {

    fun remove(removalOptions: RemovalOptions, dataTypes: DataTypeSet): Promise<Unit>

    fun removeCache(removalOptions: RemovalOptions): Promise<Unit>

    fun removeCookies(removalOptions: RemovalOptions): Promise<Unit>

    fun removeDownloads(removalOptions: RemovalOptions): Promise<Unit>

    fun removeFormData(removalOptions: RemovalOptions): Promise<Unit>

    fun removeHistory(removalOptions: RemovalOptions): Promise<Unit>

    fun removeLocalStorage(removalOptions: RemovalOptions): Promise<Unit>

    fun removePasswords(removalOptions: RemovalOptions): Promise<Unit>

    fun removePluginData(removalOptions: RemovalOptions): Promise<Unit>

    fun settings(): Promise<RemoveSettings>

}