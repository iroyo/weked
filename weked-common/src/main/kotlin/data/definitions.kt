package data

external interface OriginTypes {
    var unprotectedWeb: Boolean?
    var protectedWeb: Boolean?
    var extension: Boolean?
}

external interface RemovalOptions {
    var cookieStoreId: String?
    var originTypes: OriginTypes?
    var hostnames: Array<String>?
    var since: Any
}

external interface RemoveSettings {
    val options: RemovalOptions
    val dataToRemove: DataTypeSet
    val dataRemovalPermitted: DataTypeSet
}

external interface CookieBasedDataTypeSet {
    var cookies: Boolean?
    var indexedDB: Boolean?
}

external interface CommonDataTypeSet {
    var cache: Boolean?
    var downloads: Boolean?
    var formData: Boolean?
    var history: Boolean?
    var localStorage: Boolean?
    var passwords: Boolean?
    var pluginData: Boolean?
    var serviceWorkers: Boolean?
    var serverBoundCertificates: Boolean?
}

external interface DataTypeSet : CommonDataTypeSet, CookieBasedDataTypeSet