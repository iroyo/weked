package downloads

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
    var id: Int?
    var url: String?
    var filename: String?
    var mime: String?
    var startTime: String
    var endTime: String?
    var paused: Boolean?
    var bytesReceived: Float?
    var totalBytes: Float?
    var fileSize: Float?
    var exists: Boolean?
}

external interface DownloadItemBase : DownloadCommon {
    var referrer: String
    var incognito: Boolean
    var canResume: Boolean
    var byExtensionId: String?
    var byExtensionName: String?
    var estimatedEndTime: String?
}

external interface DownloadItem :
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
    var totalBytesGreater: Float?
    var totalBytesLess: Float?
    var filenameRegex: String?
    var urlRegex: String?
    var limit: Int?
}

external interface DownloadQuery :
    DownloadQueryCommon,
    DownloadTimeRange,
    DownloadState,
    DownloadError,
    DownloadDanger
