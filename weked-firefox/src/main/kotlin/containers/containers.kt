package containers

import containers.models.Color
import containers.models.Icon
import firefox
import jsObject
import kotlin.js.Promise


/**
 * Retrieves information about a single contextual identity.
 */
fun getContainer(cookieStoreId: String) = firefox.contextualIdentities.get(cookieStoreId)

/**
 * Retrieves all contextual identities
 */
fun queryContainers(name: String? = null) = firefox.contextualIdentities.query(jsObject<ContainerName>().apply {
    this.name = name
})

/**
 * Creates a contextual identity with the given data.
 */
fun createContainer(
    name: String,
    color: Color,
    icon: Icon,
) = firefox.contextualIdentities.create(jsObject<ContainerData>().apply {
    this.name = name
    this.icon = icon.name.toLowerCase()
    this.color = color.name.toLowerCase()
})

/**
 * Updates a contextual identity with the given data.
 */
fun updateContainer(
    id: String,
    name: String? = null,
    color: Color? = null,
    icon: Icon? = null
) = firefox.contextualIdentities.update(id, jsObject<ContainerData>().apply {
    this.name = name
    this.icon = icon?.name?.toLowerCase()
    this.color = color?.name?.toLowerCase()
})

external class Containers {

    fun get(cookieStoreId: String): Promise<ContextualIdentity>

    fun query(name: ContainerName): Promise<Array<ContextualIdentity>>

    fun create(details: ContainerData): Promise<ContextualIdentity>

    fun update(cookieStoreId: String, details: ContainerData): Promise<ContextualIdentity>
}

external class ContextualIdentity {
    val name: String
    val icon: String
    val iconUrl: String
    val color: String
    val colorCode: String
    val cookieStoreId: String
}

external interface ContainerName {
    var name: String?
}

external interface ContainerData : ContainerName {
    var color: String?
    var icon: String?
}


