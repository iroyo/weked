package data

import isChrome
import jsObject

open class SpecificOriginTargetConfiguration {

    internal val targets: DataTypeSet = jsObject()

    // @formatter:off
    fun serviceWorkers() { targets.serviceWorkers = true }
    fun webSQL() { if (isChrome) targets.webSQL = true }
    fun cacheStorage() { if (isChrome) targets.cacheStorage = true }
    fun localStorage() { targets.localStorage = true }
    fun fileSystems() { targets.fileSystems = true }
    fun indexedDB() { targets.indexedDB = true }
    fun cookies() { targets.cookies = true }
    // @formatter:on
}

class TargetConfiguration internal constructor(): SpecificOriginTargetConfiguration() {

    // @formatter:off
    fun cache() { targets.cache = true }
    fun cacheApp() { if (isChrome) targets.appcache = true }
    fun downloads() { targets.downloads = true }
    fun formData() { targets.formData = true }
    fun history() { targets.history = true }
    fun passwords() { targets.passwords = true }
    fun pluginData() { targets.pluginData = true }
    fun serverBoundCertificates() { targets.serverBoundCertificates = true }
    // @formatter:on

}
