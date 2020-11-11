package window.models

enum class WindowMode(val value: String) {
    NORMAL("normal"),
    POPUP("popup"),
    PANEL("panel"),
    APP("app"),
    DEVTOOLS("devtools");

    companion object {

        fun build(value: String?) = when (value) {
            "devtools" -> DEVTOOLS
            "normal" -> NORMAL
            "popup" -> POPUP
            "panel" -> PANEL
            "app" -> APP
            else -> null
        }

    }
}