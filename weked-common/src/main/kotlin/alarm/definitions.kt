package alarm

external interface Alarm {
    val name: String
    val scheduledTime: Double
    val periodInMinutes: Double?
}

external interface AlarmData {
    var `when`: Double?
    var periodInMinutes: Double?
    var delayInMinutesOptional: Double?
}