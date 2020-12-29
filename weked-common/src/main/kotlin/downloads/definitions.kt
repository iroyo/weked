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

external interface DownloadCommon {
    var id: Int?
    var url: String?
    var filename: String?
    var danger: String?
    var mime: String?
    var startTime: String
    var endTime: String?
    var state: String?
    var paused: Boolean?
    var error: String?
    var bytesReceived: Float?
    var totalBytes: Float?
    var fileSize: Float?
    var exists: Boolean?
}

external interface DownloadItem : DownloadCommon {
    var referrer: String
    var incognito: Boolean
    var canResume: Boolean
    var byExtensionId: String?
    var byExtensionName: String?
    var estimatedEndTime: String?
}

external interface DownloadQuery: DownloadCommon {
    var query: Array<String>?
    var startedBefore: DownloadTime?
    var startedAfter: DownloadTime?
    var endedBefore: DownloadTime?
    var endedAfter: DownloadTime?
    var totalBytesGreater: Float?
    var totalBytesLess: Float?
    var filenameRegex: String?
    var urlRegex: String?
    var limit: Int?
    var orderBy: Array<String>?
}