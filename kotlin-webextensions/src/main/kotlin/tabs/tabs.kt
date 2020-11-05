package tabs

import kotlin.js.Promise

external class Tabs {

    fun goBack(tabId: Int): Promise<Unit>

    fun goForward(tabId: Int): Promise<Unit>

    fun print()

    fun printPreview(): Promise<Unit>

    fun hide(vararg tabIds: Int)

    fun show(vararg tabIds: Int)

    fun discard(vararg tabIds: Int)

    fun getCurrent(): Promise<Tab?>

    fun get(id: Int): Promise<Tab>

    fun remove(vararg tabIds: Int): Promise<Unit>

    fun query(query: TabQuery): Promise<Array<Tab>>

    fun create(data: TabCreate): Promise<Tab?>

    fun update(id: Int, data: TabUpdate?): Promise<Tab?>

    fun duplicate(id: Int, data: TabDuplicate?): Promise<Tab?>

}
