package tabs

import browser
import jsObject
import tabs.models.*
import kotlin.js.Promise

var TabQuery.shareMode: ShareMode?
    get() = ShareMode.build(screen)
    set(value) {
        screen = when (value) {
            ShareMode.Deactivated -> false
            is ShareMode.Activated -> value.type ?: true
            null -> null
        }
    }


var TabQuery.windowMode: WindowMode?
    get() = WindowMode.build(windowType)
    set(value) {
        windowType = value?.value
    }

var TabQuery.loading: Boolean?
    get() = status?.let {
        it == "loading"
    }
    set(value) {
        status = value?.let {
            if (it) "loading" else "complete"
        }
    }

fun queryTabs(block: TabQuery.() -> Unit = {}) = browser.tabs.query(jsObject<TabQuery>().apply(block))

fun createTabs(block: TabProperties.() -> Unit = {}) = browser.tabs.create(jsObject<TabProperties>().apply(block))

external class Tabs {

    fun hide(vararg tabIds: Int)

    fun show(vararg tabIds: Int)

    fun remove(vararg tabIds: Int): Promise<Any>

    fun get(id: Int): Promise<Tab>

    fun query(query: TabQuery): Promise<Array<Tab>>

    fun create(properties: TabProperties): Promise<Tab>

}
