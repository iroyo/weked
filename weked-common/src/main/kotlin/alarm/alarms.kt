package alarm

import kotlin.js.Promise

external class Alarms {

    fun clearAll(): Promise<Boolean>

    fun clear(name: String): Promise<Boolean>

    fun create(name: String, alarmData: AlarmData): Promise<Unit>

    fun get(name: String) : Promise<Alarm>

    fun getAll() : Promise<Array<Alarm>>

}