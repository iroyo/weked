package cookies

import SingleMapListener
import browser
import cookies.models.CookieChange
import create

private val api = browser.cookies

/**
 * Fired when a cookie is set or removed.
 */
val onCookieChanged = SingleMapListener(api.onChanged, ::CookieChange)

/**
 * Deletes a cookie by name.
 */
fun removeCookie(block: Details.() -> Unit) = api.remove(create(block))

/**
 * Retrieves information about a single cookie.
 */
fun getCookie(block: Details.() -> Unit) = api.get(create(block))

/**
 * Retrieves all cookies that match a given set of filters.
 */
fun getAllCookies(block: GetAllDetails.() -> Unit) = api.getAll(create(block))

/**
 * Lists all existing cookie stores.
 */
val getAllCookieStores get() = api.getAllCookiesStores()

/**
 * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
 */
fun setCookie(block: SetDetails.() -> Unit) = api.set(create(block))
