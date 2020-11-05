package tabs

import browser
import jsObject

fun getCurrentTab() = browser.tabs.getCurrent()

fun getTab(id: Int) = browser.tabs.get(id)

fun removeTabs(vararg tabIds: Int) = browser.tabs.remove(*tabIds)

fun hideTabs(vararg tabIds: Int) = browser.tabs.hide(*tabIds)

fun showTabs(vararg tabIds: Int) = browser.tabs.show(*tabIds)

fun queryTabs(block: TabQuery.() -> Unit = {}) = browser.tabs.query(jsObject<TabQuery>().apply(block))

fun createTabs(block: TabProperties.() -> Unit = {}) = browser.tabs.create(jsObject<TabProperties>().apply(block))

fun duplicateTab(id: Int, block: TabDuplicate.() -> Unit = {}) = browser.tabs.duplicate(id, jsObject<TabDuplicate>().apply(block))

/*
var SharingType.shareMode: ShareMode
    get() = ShareMode.build(screen) ?: ShareMode.Deactivated
    set(value) {
        screen = when (value) {
            ShareMode.Deactivated -> false
            is ShareMode.Activated -> value.type ?: true
        }
    }

var WindowType.windowMode: WindowMode
    get() = WindowMode.build(windowType) ?: WindowMode.NORMAL
    set(value) {
        windowType = value.value
    }

var StatusType.loading: Boolean
    get() = status?.let { it == "loading" } ?: true
    set(value) {
        status = value.let {
            if (it) "loading" else "complete"
        }
    }

 */