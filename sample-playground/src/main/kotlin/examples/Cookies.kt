package examples

import cookies.getAllCookies

fun testAllCookies() {
    getAllCookies.then {
        it.forEach { cookie ->
            console.log("${cookie.name} - ${cookie.value}")
        }
    }
}