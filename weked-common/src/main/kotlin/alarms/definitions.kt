package alarms

external interface Alarm {
    val name: String
    val scheduledTime: Float
    val periodInMinutes: Float?
}

external interface AlarmData {
    var `when`: Float?
    var periodInMinutes: Float?
    var delayInMinutes: Float?
}