package downloads.models

import downloads.DownloadItem
import downloads.DownloadItemBase
import downloads.types.DangerType
import downloads.types.InterruptReason
import downloads.types.State
import enumFrom

class DownloadEntry(item: DownloadItem) : DownloadItemBase by item {

    val state = enumFrom(item.state, State.COMPLETE)
    val danger = enumFrom(item.danger, DangerType.SAFE)
    val interruptReason = enumFrom(item.error, InterruptReason.FILE_FAILED)
    val extensionInformation = item.byExtensionId?.let {
        ExtensionData(it, item.byExtensionName)
    }

}
