package sidebar

import kotlin.js.Promise

external class Sidebar {

    fun open(): Promise<Any>

    fun close(): Promise<Any>

    fun toggle(): Promise<Any>

    fun isOpen(details: WindowId): Promise<Boolean>

    fun getPanel(details: Details): Promise<String>

    fun setPanel(details: PanelProperties): Promise<String>

    fun getTitle(details: Details): Promise<String>

    fun setTitle(details: TitleProperties): Promise<Any>

    fun setIcon(details: IconProperties): Promise<Any>
}