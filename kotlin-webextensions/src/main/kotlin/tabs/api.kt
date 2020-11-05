package tabs

import browser
import jsObject
import tabs.models.MutedReason
import tabs.models.ShareMode
import window.models.WindowMode

internal val api = browser.tabs


fun printCurrentTab() = api.print()

fun printPreviewCurrentTab() = api.printPreview()

fun getCurrentTab() = api.getCurrent()

fun getTab(id: Int) = api.get(id)

fun removeTabs(vararg tabIds: Int) = api.remove(*tabIds)

fun hideTabs(vararg tabIds: Int) = api.hide(*tabIds)

fun showTabs(vararg tabIds: Int) = api.show(*tabIds)

fun queryTabs(block: TabQuery.() -> Unit = {}) = api.query(jsObject<TabQuery>().apply(block))

fun createTab(block: TabCreate.() -> Unit = {}) = api.create(jsObject<TabCreate>().apply(block))

fun updateTab(id: Int, block: TabUpdate.() -> Unit = {}) = api.update(id, jsObject<TabUpdate>().apply(block))

fun cloneTab(id: Int, block: TabDuplicate.() -> Unit = {}) = api.duplicate(id, jsObject<TabDuplicate>().apply(block))


var TabQuery.windowMode: WindowMode
    get() = WindowMode.build(windowType) ?: WindowMode.NORMAL
    set(value) {

        windowType = value.value
    }

var TabQuery.shareMode: ShareMode
    get() = ShareMode.build(screen) ?: ShareMode.Deactivated
    set(value) {
        screen = when (value) {
            ShareMode.Deactivated -> false
            is ShareMode.Activated -> value.type ?: true
        }
    }

var BaseTab.loading: Boolean
    get() = status?.let { it == "loading" } ?: true
    set(value) {
        status = value.let {
            if (it) "loading" else "complete"
        }
    }

val Tab.muted
    get() = mutedInfo?.muted ?: false

val Tab.mutedReason
    get() = mutedInfo?.let {
        when (it.reason) {
            "user" -> MutedReason.User
            "capture" -> MutedReason.Capture
            "extension" -> MutedReason.Extension(it.extensionId)
            else -> null
        }
    }
