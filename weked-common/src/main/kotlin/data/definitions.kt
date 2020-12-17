package data

external interface OriginTypes {
    var unprotectedWeb: Boolean?
    var protectedWeb: Boolean?
    var extension: Boolean?
}

external interface CommonRemoveOptions {
    var since: Double
    var originTypes: OriginTypes?
}

external interface RemovalOptions : CommonRemoveOptions {
    var cookieStoreId: String?
    var hostnames: Array<String>?
    var excludeOrigins: Array<String>?
    var origins: Array<String>?
}

external interface RemoveSettings {
    val options: RemovalOptions
    val dataToRemove: DataTypeSet
    val dataRemovalPermitted: DataTypeSet
}

external interface DataTypeSet {
    var cache: Boolean?
    var appcache: Boolean?
    var cacheStorage: Boolean?
    var cookies: Boolean?
    var downloads: Boolean?
    var formData: Boolean?
    var fileSystems: Boolean?
    var history: Boolean?
    var webSQL: Boolean?
    var indexedDB: Boolean?
    var localStorage: Boolean?
    var passwords: Boolean?
    var pluginData: Boolean?
    var serviceWorkers: Boolean?
    var serverBoundCertificates: Boolean?
}