package window.models

import window.models.ResizableWindowState.Docked
import window.models.ResizableWindowState.Normal
import window.models.UnresizableWindowState.*

sealed class WindowState {
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

sealed class ResizableWindowState(override val name: String): WindowState() {
    object Normal : ResizableWindowState("normal")
    object Docked : ResizableWindowState("docked")
}

sealed class UnresizableWindowState(override val name: String) : WindowState() {
    object Minimized : UnresizableWindowState("minimized")
    object Maximized : UnresizableWindowState("maximized")
    object Fullscreen : UnresizableWindowState("fullscreen")
}