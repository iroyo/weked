package settings

import SingleParameterCallback
import kotlin.js.Promise

typealias BooleanSetting = Setting<Boolean>

external interface GetDetails {
    var incognito: Boolean
}

external interface SetDetails<T> {
    var scope: String?
    var value: T
}

external interface ClearDetails {
    var scope: String?
}

external interface Result<T> {
    val incognitoSpecific: Boolean?
    val levelOfControl: String?
    val value: T
}

external class Setting<T> {

    val onChange: SingleParameterCallback<Result<T>>
    /**
     * Gets the value of a setting.
     */
    fun get(details: GetDetails): Promise<Result<T>>

    /**
     * Sets the value of a setting.
     */
    fun set(details: SetDetails<T>): Promise<Unit>

    /**
     * Clears the setting, restoring any default value.
     */
    fun clear(details: ClearDetails): Promise<Unit>
}