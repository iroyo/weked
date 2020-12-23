package commands

import SingleParameterCallback
import kotlin.js.Promise

open external class Commands {

    val onCommand: SingleParameterCallback<String>

    fun getAll(): Promise<Array<Command>>

}