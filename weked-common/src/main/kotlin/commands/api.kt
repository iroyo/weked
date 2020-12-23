package commands

import browser

/**
 * Object representing a command. This contains the information specified for the command in the commands manifest.json key.
 */
val onCommandExecuted = browser.commands.onCommand

/**
 * Gets all registered commands for this extension.
 */
val getAllCommands get() = browser.commands.getAll()