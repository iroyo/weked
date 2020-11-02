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

fun getCurrentTab() = browser.tabs.getCurrent()

fun getTab(id: Int) = browser.tabs.get(id)

fun removeTabs(vararg tabIds: Int) = browser.tabs.remove(*tabIds)

fun hideTabs(vararg tabIds: Int) = browser.tabs.hide(*tabIds)

fun showTabs(vararg tabIds: Int) = browser.tabs.show(*tabIds)

fun queryTabs(block: TabQuery.() -> Unit = {}) = browser.tabs.query(jsObject<TabQuery>().apply(block))

fun createTabs(block: TabProperties.() -> Unit = {}) = browser.tabs.create(jsObject<TabProperties>().apply(block))

fun duplicateTab(id: Int, block: TabDuplicate.() -> Unit = {}) = browser.tabs.duplicate(id, jsObject<TabDuplicate>().apply(block))

external class Tabs {

    fun hide(vararg tabIds: Int)

    fun show(vararg tabIds: Int)

    fun remove(vararg tabIds: Int): Promise<Unit>

    fun getCurrent(): Promise<Tab?>

    fun get(id: Int): Promise<Tab>

    fun query(query: TabQuery): Promise<Array<Tab>>

    fun create(properties: TabProperties): Promise<Tab?>

    fun duplicate(id: Int, duplicateProperties: TabDuplicate?): Promise<Tab?>

}
