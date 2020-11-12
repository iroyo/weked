package containers

import browser
import containers.models.Color
import containers.models.Icon
import jsObject

internal val api = browser.contextualIdentities

/**
 * Retrieves information about a single contextual identity.
 * @throws InvalidContextualIdentity when no cookieStoreId matches
 */
fun getContainer(cookieStoreId: String) = api.get(cookieStoreId).then(::Container)

/**
 * Retrieves all contextual identities
 * * If name is null all containers are returned
 * * If name does not match any name's container an empty array will be returned
 */
fun queryContainers(name: String? = null) = api.query(jsObject<ContainerName>().apply {
    this.name = name
}).then {
    it.map(::Container)
}

/**
 * Creates a contextual identity with the given data.
 */
fun createContainer(
    name: String,
    color: Color,
    icon: Icon,
) = api.create(jsObject<ContainerData>().apply {
    this.name = name
    this.icon = icon.name.toLowerCase()
    this.color = color.name.toLowerCase()
}).then(::Container)

/**
 * Updates a contextual identity with the given data.
 * @throws InvalidContextualIdentity when no cookieStoreId matches
 */
fun updateContainer(
    id: String,
    name: String? = null,
    color: Color? = null,
    icon: Icon? = null
) = api.update(id, jsObject<ContainerData>().apply {
    this.name = name
    this.icon = icon?.name?.toLowerCase()
    this.color = color?.name?.toLowerCase()
}).then(::Container)
