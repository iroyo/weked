package examples

import find.findContent
import find.highlightResults

fun findWord(value: String) {
    findContent(value) { entireWord = true}.then {
        highlightResults()
    }
}