package containers

external class ContextualIdentity {
    val name: String
    val icon: String
    val iconUrl: String
    val color: String
    val colorCode: String
    val cookieStoreId: String
}

external interface ContainerName {
    var name: String?
}

external interface ContainerData : ContainerName {
    var color: String?
    var icon: String?
}
