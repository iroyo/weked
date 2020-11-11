package tabs.models

sealed class MutedReason {
    object User : MutedReason()
    object Capture : MutedReason()
    class Extension(val id: String?): MutedReason()
}