package downloads.models

import Delta
import create
import downloads.DownloadDelta
import downloads.DownloadDeltaBase
import downloads.types.DangerType
import downloads.types.InterruptReason
import downloads.types.State
import enumFrom

class DownloadEntryDelta(item: DownloadDelta) : DownloadDeltaBase by item {

    val state = create<Delta<State>> {
        this.current = item.state?.current?.let { enumFrom(it, State.COMPLETE) }
        this.previous = item.state?.previous?.let { enumFrom(it, State.COMPLETE) }
    }
    val danger = create<Delta<DangerType>> {
        this.current = item.danger?.current?.let { enumFrom(it, DangerType.SAFE) }
        this.previous = item.danger?.previous?.let { enumFrom(it, DangerType.SAFE) }
    }
    val interruptReason = create<Delta<InterruptReason>> {
        this.current = item.error?.current?.let { enumFrom(it, InterruptReason.FILE_FAILED) }
        this.previous = item.error?.previous?.let { enumFrom(it, InterruptReason.FILE_FAILED) }
    }

}
