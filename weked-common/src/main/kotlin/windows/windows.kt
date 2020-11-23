package windows

import Listener
import kotlin.js.Promise

external class Windows {

    val onCreated: Listener<(Window) -> Unit>

    val onRemoved: Listener<(Int) -> Unit>

    val onFocusChanged: Listener<(Int) -> Unit>

    fun get(windowId: Int, properties: GetProperties? = definedExternally): Promise<Window>

    fun getCurrent(properties: GetProperties? = definedExternally): Promise<Window>

    fun getLastFocused(properties: GetProperties? = definedExternally): Promise<Window>

    fun getAll(properties: GetProperties? = definedExternally): Promise<Array<Window>>

    fun create(properties: CreateProperties? = definedExternally): Promise<Window>

    fun update(windowId: Int, properties: UpdateProperties? = definedExternally): Promise<Window>

    fun remove(windowId: Int): Promise<Any>

}