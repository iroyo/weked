package commands

external interface Command {
    var name: String?
    var description: String?
    var shortcut: String?
}