package downloads.models

import downloads.DownloadItem
import downloads.DownloadItemBase
import enumFrom

class DownloadEntry(item: DownloadItem) : DownloadItemBase by item {

    val state = enumFrom(item.state, State.COMPLETE)
    val danger = enumFrom(item.danger, DangerType.SAFE)
    val interruptReason = enumFrom(item.error, InterruptReason.FILE_FAILED)
    val extensionInformation = item.byExtensionId?.let {
        ExtensionData(it, item.byExtensionName)
    }

}
