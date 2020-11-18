package window

import window.models.Window
import kotlin.js.Promise

external class Windows {

    fun get(windowId: Int, properties: GetProperties? = definedExternally): Promise<Window>

    fun getCurrent(properties: GetProperties? = definedExternally): Promise<Window>

    fun getLastFocused(properties: GetProperties? = definedExternally): Promise<Window>

    fun getAll(properties: GetProperties? = definedExternally): Promise<Array<Window>>

    fun create(properties: CreateProperties? = definedExternally): Promise<Window>

    fun update(properties: UpdateProperties? = definedExternally): Promise<Window>

}