package windows.models

import windows.models.ResizableWindowState.Docked
import windows.models.ResizableWindowState.Normal
import windows.models.UnresizableWindowState.*

sealed class BrowserWindowState {
    abstract val name: String

    companion object {
        fun create(value: String?) = when(value) {
            Normal.name -> Normal
            Docked.name -> Docked
            Minimized.name -> Minimized
            Maximized.name -> Maximized
            Fullscreen.name -> Fullscreen
            else -> null
        }
    }
}

sealed class ResizableWindowState(override val name: String): BrowserWindowState() {
    object Normal : ResizableWindowState("normal")
    object Docked : ResizableWindowState("docked")
}

sealed class UnresizableWindowState(override val name: String) : BrowserWindowState() {
    object Minimized : UnresizableWindowState("minimized")
    object Maximized : UnresizableWindowState("maximized")
    object Fullscreen : UnresizableWindowState("fullscreen")
}