package commands

import kotlin.js.Promise

external class FirefoxCommands: Commands {

    fun reset(name: String): Promise<Unit>

    fun update(details: Command): Promise<Unit>

}