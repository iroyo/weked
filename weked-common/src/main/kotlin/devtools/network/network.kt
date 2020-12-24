package devtools.network

import SingleParameterCallback
import kotlin.js.Promise

external class Network {

    val onNavigated: SingleParameterCallback<String>

    val onRequestFinished: SingleParameterCallback<Request>

    fun getHAR(): Promise<HarLog>

}