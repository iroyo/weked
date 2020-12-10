package settings.models

import enumFrom
import settings.Result
import settings.models.LevelOfControl.CONTROLLABLE_BY_THIS_EXTENSION

data class SettingResult<T>(
    val levelOfControl: LevelOfControl,
    val incognito: Boolean,
    val value: T
) {
    constructor(result: Result<T>) : this(
        value = result.value,
        incognito = result.incognitoSpecific ?: false,
        levelOfControl = enumFrom(result.levelOfControl, CONTROLLABLE_BY_THIS_EXTENSION),
    )
}