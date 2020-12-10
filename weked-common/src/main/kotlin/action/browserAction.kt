package action

import SingleParameterCallback
import tabs.Tab
import kotlin.js.Promise

open external class BrowserAction {

    val onClicked: SingleParameterCallback<Tab>

    fun setTitle(data: TitleData): Promise<Unit>

    fun getTitle(data: TabId): Promise<String>

    fun setIcon(data: IconDataViaPath): Promise<Unit>

    fun setIcon(data: IconDataViaImageData): Promise<Unit>

    fun setPopup(data: PopupData): Promise<Unit>

    fun getPopup(data: TabId): Promise<String>

    fun openPopup(): Promise<Unit>

    fun setBadgeText(data: BadgeTextData): Promise<Unit>

    fun getBadgeText(data: TabId): Promise<String>

    fun setBadgeBackgroundColor(data: BadgeColorStringData): Promise<Unit>

    fun setBadgeBackgroundColor(data: BadgeColorArrayData): Promise<Unit>

    fun getBadgeBackgroundColor(data: TabId): Promise<ColorArray>

    fun enable(data: TabId): Promise<Unit>

    fun disable(data: TabId): Promise<Unit>

}