package storage.models

import storage.Changes

data class DataUpdate(
    val where: StorageType,
    val changes: Changes
)