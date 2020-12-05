package alarm

import SingleParameterCallback
import kotlin.js.Promise

external class Alarms {

    val onAlarm: SingleParameterCallback<Alarm>

    fun clearAll(): Promise<Boolean>

    fun clear(name: String): Promise<Boolean>

    fun create(name: String, alarmData: AlarmData): Promise<Unit>

    fun get(name: String) : Promise<Alarm>

    fun getAll() : Promise<Array<Alarm>>

}