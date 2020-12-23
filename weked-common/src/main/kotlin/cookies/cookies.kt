package cookies

import SingleParameterCallback
import kotlin.js.Promise

external class Cookies {

    val onChanged: SingleParameterCallback<ChangeDetails>

    fun get(details: Details): Promise<Cookie?>

    fun getAll(details: GetAllDetails): Promise<Array<Cookie>>

    fun getAllCookiesStores(): Promise<Array<CookieStore>>

    fun remove(details: Details): Promise<Cookie>

    fun set(details: SetDetails): Promise<Cookie>
}