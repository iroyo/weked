package containers

import Listener
import kotlin.js.Promise

external class Containers {

    val onUpdated: Listener<(Result) -> Unit>

    val onCreated: Listener<(Result) -> Unit>

    val onRemoved: Listener<(Result) -> Unit>

    fun get(cookieStoreId: String): Promise<ContextualIdentity>

    fun query(name: ContainerName): Promise<Array<ContextualIdentity>>

    fun create(details: ContainerData): Promise<ContextualIdentity>

    fun update(cookieStoreId: String, details: ContainerData): Promise<ContextualIdentity>
}