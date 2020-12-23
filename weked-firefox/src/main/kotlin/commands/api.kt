package commands

import browser
import create

private val api = browser.commands

/**
 * Reset the given command's description and shortcut to the values given in the manifest key
 */
fun resetCommand(name: String) = api.reset(name)

/**
 * Change the description or keyboard shortcut for the given command.
 */
fun updateCommand(action: Command.() -> Unit) = api.update(create(action))