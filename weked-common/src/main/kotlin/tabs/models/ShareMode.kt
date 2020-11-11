package tabs.models

sealed class ShareMode {
    object Deactivated: ShareMode()
    class Activated internal constructor(val type: String? = null): ShareMode() {

        companion object {
            fun forScreen() = Activated("Screen")
            fun forWindow() = Activated("Window")
            fun forApplication() = Activated("Application")
        }
    }

    companion object {

        fun activated() = Activated()

        internal fun build(value: Any?) = when(value) {
            is Boolean -> if (value) Activated() else Deactivated
            is String -> Activated(value)
            else -> null
        }
    }
}
