package cookies.models

import cookies.ChangeDetails
import cookies.Cookie
import enumFrom

data class CookieChange(
    val cookie: Cookie,
    val removed: Boolean,
    val cause: ChangedCause
) {
    constructor(cookie: ChangeDetails) : this(
        cookie.cookie,
        cookie.removed,
        enumFrom(cookie.cause, ChangedCause.EVICTED)
    )
}