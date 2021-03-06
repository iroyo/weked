package downloads.types

enum class InterruptReason {
    FILE_FAILED,
    FILE_ACCESS_DENIED,
    FILE_NO_SPACE,
    FILE_NAME_TOO_LONG,
    FILE_TOO_LARGE,
    FILE_VIRUS_INFECTED,
    FILE_TRANSIENT_ERROR,
    FILE_BLOCKED,
    FILE_SECURITY_CHECK_FAILED,
    FILE_TOO_SHORT,
    NETWORK_FAILED,
    NETWORK_TIMEOUT,
    NETWORK_DISCONNECTED,
    NETWORK_SERVER_DOWN,
    NETWORK_INVALID_REQUEST,
    SERVER_FAILED,
    SERVER_NO_RANGE,
    SERVER_BAD_CONTENT,
    SERVER_UNAUTHORIZED,
    SERVER_CERT_PROBLEM,
    SERVER_FORBIDDEN,
    USER_CANCELED,
    USER_SHUTDOWN,
    CRASH,
}