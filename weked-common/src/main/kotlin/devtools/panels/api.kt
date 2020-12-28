package devtools.panels

import browser

private val api = browser.devtools.panels

/**
 * The name of the current devtools theme.
 */
val devtoolsThemeName = api.themeName

/**
 * An ElementsPanel represents the HTML/CSS inspector in the browser's devtools.
 * This is called the Page Inspector in Firefox and the Elements panel in Chrome.
 */
val devtoolsElements = api.elements

/**
 * Adds a new panel to the devtools.
 * * This function takes: a title, a URL to an icon file, and a URL to an HTML file.
 * * It creates a new panel in the devtools, whose content is specified by the HTML file.
 * * It @return a Promise that resolves to an ExtensionPanel object representing the new panel.
 */
fun createPanel(title: String, iconPath: String, pagePath: String) = api.create(title, iconPath, pagePath)