package devtools.inspectedWindow

import kotlin.js.Promise

external class InspectedWindow {
    val tabId: Int
    fun eval(expression: String, options: EvalOptions?): Promise<Array<Any>>
    fun reload(options: ReloadOptions?)
}

external interface EvalOptions {
    var frameURLOptional: String?
    var contextSecurityOrigin: String?
    var useContentScriptContextOptional: Boolean?
}

external interface ReloadOptions {
    var ignoreCacheOptional: Boolean?
    var injectedScript: String?
    var userAgent: String?
}