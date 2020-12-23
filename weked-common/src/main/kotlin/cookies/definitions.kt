package cookies

external interface CommonData {
    var domain: String?
    var name: String?
    var path: String?
    var storeId: String?
    var secure: Boolean?
    var session: Boolean?
}

external interface Cookie {
    val expirationDate: String?
    val hostOnly: Boolean
    val httpOnly: Boolean
    val sameSite: String
    val value: String
    val domain: String
    val name: String
    val path: String
    val storeId: String
    val secure: Boolean
    val session: Boolean
}

external interface CookieStore {
    val id: String
    val incognito: Boolean
    val tabIds: Array<Int>
}



external interface Details {
    var name: String
    var url: String
    var storeId: String?
}

external interface ChangeDetails {
    val removed: Boolean
    val cookie: Cookie
    val cause: String
}

external interface GetAllDetails: CommonData {
    var url: String?
}

external interface SetDetails : CommonData {
    var expirationDateOptional: Double?
    var url: String
}

