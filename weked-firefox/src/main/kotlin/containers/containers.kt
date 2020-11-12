package containers

import kotlin.js.Promise

external class Containers {

    fun get(cookieStoreId: String): Promise<ContextualIdentity>

    fun query(name: ContainerName): Promise<Array<ContextualIdentity>>

    fun create(details: ContainerData): Promise<ContextualIdentity>

    fun update(cookieStoreId: String, details: ContainerData): Promise<ContextualIdentity>
}