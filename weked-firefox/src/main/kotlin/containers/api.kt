package containers

import Listener
import browser
import containers.models.Color
import containers.models.Icon
import create

internal val api = browser.contextualIdentities

internal class CustomListener(private val event: Listener<(Result) -> Unit>) : Listener<(Container) -> Unit> {
    override fun addListener(listener: (Container) -> Unit) = event.addListener {
        listener(Container(it.contextualIdentity))
    }

    override fun removeListener(listener: (Container) -> Unit) = event.removeListener {
        listener(Container(it.contextualIdentity))
    }

    override fun hasListener(listener: (Container) -> Unit) = event.hasListener {
        listener(Container(it.contextualIdentity))
    }
}

//region EVENTS: container events

/**
 * Fired when a container is updated.
 */
val onContainerUpdated: Listener<(Container) -> Unit> = CustomListener(api.onUpdated)

/**
 * Fired when a new container is created.
 */
val onContainerCreated: Listener<(Container) -> Unit> = CustomListener(api.onCreated)

/**
 * Fired when a container is removed.
 */
val onContainerRemoved: Listener<(Container) -> Unit> = CustomListener(api.onRemoved)

//endregion

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
fun queryContainers(name: String? = null) = api.query(create { this.name = name }).then {
    it.map(::Container)
}

/**
 * Creates a contextual identity with the given data.
 */
fun createContainer(
    name: String,
    color: Color,
    icon: Icon,
) = api.create(create {
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
) = api.update(id, create {
    this.name = name
    this.icon = icon?.name?.toLowerCase()
    this.color = color?.name?.toLowerCase()
}).then(::Container)

/**
 * Removes a contextual identity, given its cookie store ID.
 */
fun removeContainer(id: String) = api.remove(id).then(::Container)
