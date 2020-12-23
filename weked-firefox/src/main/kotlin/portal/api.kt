package portal

import SingleMapListener
import browser
import enumFrom

private val api = browser.captivePortal

/**
 * Fires when the captive portal state changes.
 */
val onCaptivePortalStateChange = SingleMapListener(api.onStateChanged) {
    enumFrom(it.state, CaptivePortalState.UNKNOWN)
}

/**
 * Fires when the captive portal service determines that the user can connect to the internet.
 */
val onCaptivePortalConnectivityAvailable = api.onConnectivityAvailable

/**
 * Returns the time since the last request was completed.
 */
val getCaptivePortalLastChecked get() = api.getLastChecked()

/**
 * Returns the portal state as one of unknown, not_captive, unlocked_portal, or locked_portal.
 */
val getCaptivePortalState get() = api.getState().then { enumFrom(it, CaptivePortalState.UNKNOWN) }