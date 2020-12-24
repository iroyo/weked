package devtools.network

import browser

/**
 * Get a HAR log for the page loaded in the current tab.
 */
val getNetworkHAR get() = browser.devtools.network.getHAR()

/**
 * Fired when the user navigates the inspected window to a new page.
 */
val onNetworkNavigated = browser.devtools.network.onNavigated

/**
 * Fired when the a network request has finished and its details are available to the extension.
 */
val onNetworkRequestFinished = browser.devtools.network.onRequestFinished