package containers

import containers.models.Color
import containers.models.Icon

data class Container(
    val cookieStoreId: String,
    val name: String,
    val color: Color,
    val icon: Icon,
) {
    constructor(contextualIdentity: ContextualIdentity): this(
        contextualIdentity.cookieStoreId,
        contextualIdentity.name,
        Color.valueOf(contextualIdentity.color),
        Icon.valueOf(contextualIdentity.icon)
    )
}