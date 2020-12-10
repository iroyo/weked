package settings

import SingleMapListener
import browser
import jsObject
import settings.models.SettingResult

private val api = browser.browserSettings

fun <T> onSettingChanged(setting: FirefoxBrowserSettings.() -> Setting<T>) =
    SingleMapListener(setting(api).onChange, ::SettingResult)

fun <T> get(incognito: Boolean = false, setting: FirefoxBrowserSettings.() -> Setting<T>) =
    setting(api).get(jsObject<GetDetails>().apply {
        this.incognito = incognito
    }).then(::SettingResult)

fun <T> set(value: T, setting: FirefoxBrowserSettings.() -> Setting<T>) =
    setting(api).set(jsObject<SetDetails<T>>().apply {
        this.value = value
    })
