package sidebar

import kotlin.js.Promise

external class Sidebar {

    fun open(): Promise<Any>

    fun close(): Promise<Any>

    fun toggle(): Promise<Any>
}