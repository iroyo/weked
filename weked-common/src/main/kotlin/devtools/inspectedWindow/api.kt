package devtools.inspectedWindow

import browser
import create

/**
 * The ID of the window that the developer tools are attached to.
 */
val inspectedWindowTabId = browser.devtools.inspectedWindow.tabId

/**
 * Evaluate some JavaScript in the target window.
 */
fun onInspectedWindowEvaluate(expression: String, block: EvalOptions.() -> Unit = {}) =
    browser.devtools.inspectedWindow.eval(expression, create(block))

/**
 * Reload the target window's document.
 */
fun onInspectedWindowReload(block: ReloadOptions.() -> Unit = {}) =
    browser.devtools.inspectedWindow.reload(create(block))

