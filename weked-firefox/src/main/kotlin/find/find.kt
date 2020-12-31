package find

import kotlin.js.Promise

external class Find {
    fun find(query: String, options: FindOptions? = definedExternally): Promise<FindResult>
    fun highlightResults()
    fun removeHighlighting()
}