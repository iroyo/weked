package action

import browser

private val api = browser.browserAction

/**
 * Sets the badge's text color.
 */
fun setBrowserActionBadgeTextColor(color: String?, tabId: Int? = null) = api.setBadgeTextColor(build<BadgeColorStringData>(tabId).apply {
    this.color = color
})

/**
 * Sets the badge's text color.
 */
fun setBrowserActionBadgeTextColor(color: ColorArray?, tabId: Int? = null) = api.setBadgeTextColor(build<BadgeColorArrayData>(tabId).apply {
    this.color = color
})

/**
 * Gets the badge's text color.
 */
fun getBrowserActionBadgeTextColor(tabId: Int? = null) = api.getBadgeTextColor(build(tabId))

/**
 * Checks whether the browser action is enabled or not.
 */
fun isBrowserActionEnabled(tabId: Int? = null) = api.isEnabled(build(tabId))