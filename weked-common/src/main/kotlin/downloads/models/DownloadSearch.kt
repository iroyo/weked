package downloads.models

import downloads.DownloadQuery
import downloads.DownloadQueryCommon
import jsObject

class DownloadSearch(internal val data: DownloadQuery = jsObject()) : DownloadQueryCommon by data {

    // TODO

}