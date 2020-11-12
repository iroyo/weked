package sidebar

import browser

internal val api = browser.sidebarAction

fun openSidebar() = api.open()

fun closeSidebar() = api.close()

fun toggleSidebar() = api.toggle()

