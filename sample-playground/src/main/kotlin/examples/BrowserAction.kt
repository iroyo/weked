package examples

import action.setBrowserActionBadgeBackgroundColor
import action.setBrowserActionBadgeText

fun setBadgeText() {
   setBrowserActionBadgeText("IR").then {
        setBrowserActionBadgeBackgroundColor(arrayOf(25, 209, 181, 255))
    }
}