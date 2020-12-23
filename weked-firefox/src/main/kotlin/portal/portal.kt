package portal

import SingleParameterCallback
import kotlin.js.Promise

external class CaptivePortal {

    val onConnectivityAvailable: SingleParameterCallback<String>

    val onStateChanged: SingleParameterCallback<StateChange>

    fun getLastChecked(): Promise<Double>

    fun getState(): Promise<String>
}