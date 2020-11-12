import containers.Containers

external val browser: Firefox

external class Firefox : Browser {
    val contextualIdentities: Containers
}