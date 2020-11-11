import containers.Containers

external val firefox: Firefox

external class Firefox : Browser {
    val contextualIdentities: Containers
}