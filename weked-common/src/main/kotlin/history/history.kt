package history

import SingleParameterCallback
import kotlin.js.Promise

external class History {
    val onVisitRemoved: SingleParameterCallback<VisitRemove>
    val onTitleChanged: SingleParameterCallback<TitleChange>
    val onVisited: SingleParameterCallback<HistoryItem>
    fun search(query: SearchOptions): Promise<Array<HistoryItem>>
    fun getVisits(details: UrlProvider): Promise<Array<VisitedItem>>
    fun addUrl(details: UrlOptions) : Promise<Unit>
    fun deleteUrl(details: UrlProvider): Promise<Unit>
    fun deleteRange(details: RangeProvider): Promise<Unit>
    fun deleteAll(): Promise<Unit>
}
