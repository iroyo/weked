package window.models

enum class WindowNormalState {
    NORMAL,
    DOCKED,
}

enum class WindowState {
    MINIMIZED,
    MAXIMIZED,
    FULLSCREEN;

    val string get() = name.toLowerCase()
}