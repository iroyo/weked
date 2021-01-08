package find

import browser
import create

private val api = browser.find

/**
 * Find text in a web page.
 */
fun findContent(value: String, block: FindOptions.() -> Unit = {}) = api.find(value, create(block))

/**
 * Highlight the last set of matches found.
 * * Don't scroll to highlighted item. Defaults to true.
 */
fun highlightResults(block: HighlightOptions.() -> Unit = {}) = api.highlightResults(create(block))

/**
 * Remove any highlighting.
 */
fun removeHighlighting() = api.removeHighlighting()