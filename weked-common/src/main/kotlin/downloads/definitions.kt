package downloads

import BooleanDelta
import DoubleDelta
import StringDelta

typealias DownloadTime = Any

external interface Header {
    var name: String
    var value: String
}

external interface DownloadOptions {
    var headers: Array<Header>
    var allowHttpErrors: Boolean?
    var saveAs: Boolean?
    var incognito: Boolean?
    var conflictAction: String?
    var filename: String?
    var body: String?
    var method: String?
    var url: String
}

external interface DownloadState {
    var state: String?
}

external interface DownloadError {
    var error: String?
}

external interface DownloadDanger {
    var danger: String?
}

external interface DownloadCommon {
    var id: Int
    var url: String
    var mime: String
    var filename: String
    var fileSize: Float
    var endTime: String?
    var startTime: String?
    var totalBytes: Float
    var bytesReceived: Float
    var paused: Boolean
    var exists: Boolean
}

external interface ExtensionInformation {
    val byExtensionId: String?
    val byExtensionName: String?
}

external interface DownloadItemBase : DownloadCommon {
    val referrer: String
    val incognito: Boolean
    val canResume: Boolean
    val estimatedEndTime: String?
}

external interface DownloadItem :
    ExtensionInformation,
    DownloadItemBase,
    DownloadState,
    DownloadError,
    DownloadDanger

external interface DownloadTimeRange {
    var startedBefore: DownloadTime?
    var startedAfter: DownloadTime?
    var endedBefore: DownloadTime?
    var endedAfter: DownloadTime?
}

external interface DownloadQueryCommon : DownloadCommon {
    var query: Array<String>?
    var orderBy: Array<String>?
    var totalBytesGreater: Float
    var totalBytesLess: Float
    var filenameRegex: String?
    var urlRegex: String?
    var limit: Int
}

external interface DownloadQuery :
    DownloadQueryCommon,
    DownloadTimeRange,
    DownloadState,
    DownloadError,
    DownloadDanger


external interface FileIconOptions {
    var size: Int?
}

external interface DangerDelta {
    var danger: StringDelta?
}

external interface StateDelta {
    var state: StringDelta?
}

external interface ErrorDelta {
    var error: StringDelta?
}

external interface DownloadDeltaBase {
    val id: Int
    var url: StringDelta?
    var mime: StringDelta?
    var filename: StringDelta?
    var fileSize: DoubleDelta?
    var startTime: StringDelta?
    var endTime: StringDelta?
    var canResume: BooleanDelta?
    var paused: BooleanDelta?
    var totalBytes: DoubleDelta?
    var exists: BooleanDelta?
}

external interface DownloadDelta:
    DownloadDeltaBase,
    StateDelta,
    ErrorDelta,
    DangerDelta
