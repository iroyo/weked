package history.models

import enumFrom
import history.VisitedItem
import kotlin.js.Date

data class BrowserVisitRecord(
    val id: String,
    val visitedId: String,
    val referringVisitId: String,
    val transition: TransitionType,
    val visitTime: Date?,
) {

    constructor(item: VisitedItem): this(
        item.id,
        item.visitedId,
        item.referringVisitId,
        enumFrom(item.transition, TransitionType.LINK),
        item.visitTime?.let { Date(it) }
    )
}