package action

import browser
import jsObject

private val api = browser.browserAction

fun <T : TabId> build(tabId: Int?) = jsObject<T>().apply {
    tabId?.let { this.tabId = it }
}

/**
 * Fired when a browser action icon is clicked.
 * * This event will not fire if the browser action has a popup
 */
val onBrowserActionClicked = api.onClicked

/**
 * Sets the browser action's title. This will be displayed in a tooltip.
 */
fun setBrowserActionTitle(title: String, tabId: Int? = null) = api.setTitle(build<TitleData>(tabId).apply {
    this.title = title
})


/**
 * Gets the browser action's title.
 */
fun getBrowserActionTitle(tabId: Int? = null) = api.getTitle(build(tabId))

/**
 * Sets the browser action's icon.
 */
fun setBrowserActionIcon(path: String?, tabId: Int? = null) = api.setIcon(build<IconDataViaPath>(tabId).apply {
    this.path = path
})

/**
 * Sets the browser action's icon.
 */
fun setBrowserActionIcon(imageData: ImageDataType?, tabId: Int? = null) = api.setIcon(build<IconDataViaImageData>(tabId).apply {
    this.imageData = imageData
})

/**
 * Sets the HTML document to be opened as a popup when the user clicks on the browser action's icon.
 */
fun setBrowserActionPopup(popup: String?, tabId: Int? = null) = api.setPopup(build<PopupData>(tabId).apply {
    this.popup = popup
})

/**
 * Gets the HTML document set as the browser action's popup.
 */
fun getBrowserActionPopup(tabId: Int? = null) = api.getPopup(build(tabId))

/**
 * Open the browser action's popup.
 */
fun openBrowserAction() = api.openPopup()

/**
 * Sets the browser action's badge text. The badge is displayed on top of the icon.
 */
fun setBrowserActionBadgeText(text: String?, tabId: Int? = null) = api.setBadgeText(build<BadgeTextData>(tabId).apply {
    this.text = text
})

/**
 * Gets the browser action's badge text.
 */
fun getBrowserActionBadgeText(tabId: Int? = null) = api.getBadgeText(build(tabId))

/**
 * Sets the badge's background color.
 */
fun setBrowserActionBadgeBackgroundColor(color: String?, tabId: Int? = null) = api.setBadgeBackgroundColor(build<BadgeColorStringData>(tabId).apply {
    this.color = color
})

/**
 * Sets the badge's background color.
 */
fun setBrowserActionBadgeBackgroundColor(color: ColorArray?, tabId: Int? = null) = api.setBadgeBackgroundColor(build<BadgeColorArrayData>(tabId).apply {
    this.color = color
})

/**
 * Gets the badge's background color.
 */
fun getBrowserActionBadgeBackgroundColor(tabId: Int? = null) = api.getBadgeBackgroundColor(build(tabId))

/**
 * Enables the browser action for a tab. By default, browser actions are enabled for all tabs.
 */
fun enableBrowserAction(tabId: Int? = null) = api.enable(build(tabId))

/**
 * Disables the browser action for a tab, meaning that it cannot be clicked when that tab is active.
 */
fun disableBrowserAction(tabId: Int? = null) = api.disable(build(tabId))