package examples

import commands.onCommandExecuted

fun registerListener() {
    onCommandExecuted.addListener {
        console.log(it)
    }
}