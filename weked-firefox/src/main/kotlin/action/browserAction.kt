package action

import kotlin.js.Promise

external class FirefoxBrowserAction : BrowserAction {

    fun setBadgeTextColor(data: BadgeColorStringData): Promise<Unit>

    fun setBadgeTextColor(data: BadgeColorArrayData): Promise<Unit>

    fun getBadgeTextColor(data: TabId): Promise<ColorArray>

    fun isEnabled(data: TabId): Promise<Boolean>

}