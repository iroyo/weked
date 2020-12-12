package data.models

open class Options internal constructor(
    internal open val time: Double = 0.0,
    internal open val originFromExtensions: Boolean? = null,
    internal open val originFromNormalWebs: Boolean? = null,
    internal open val originFromInstalledWebs: Boolean? = null,
    internal open val hostNames: List<String>? = null,
    internal open val excludingOrigins: List<String>?  = null,
    internal open val includingOrigins: List<String>?  = null,
)

data class RemovalConfiguration(
    override val time: Double = 0.0,
    override val originFromExtensions: Boolean? = null,
    override val originFromNormalWebs: Boolean? = null,
    override val originFromInstalledWebs: Boolean? = null,
    override val hostNames: List<String>? = null,
    override val excludingOrigins: List<String>? = null,
    override val includingOrigins: List<String>? = null,
) : AllConfigurations,
    Options(),
    NextConfigurationsExceptHosts,
    NextConfigurationsExceptTemporal,
    NextConfigurationsExceptOriginType,
    NextConfigurationsExceptIncludeOrigins,
    NextConfigurationsExceptExcludeOrigins {

    override val data: Options get() = this

    override fun from(timeInMillis: Double): NextConfigurationsExceptTemporal = copy(time = timeInMillis)
    override fun whenHostNameMatches(vararg value: String): NextConfigurationsExceptHosts = copy(hostNames = listOf(*value))
    override fun fromAllOrigins(): NextConfigurationsExceptOriginType = fromOrigins(normalWebs = true, installedWebs = true, extensions = true)
    override fun fromOriginsIncluding(vararg value: String): NextConfigurationsExceptIncludeOrigins = copy(includingOrigins = listOf(*value))
    override fun fromOriginsExcluding(vararg value: String): NextConfigurationsExceptExcludeOrigins = copy(excludingOrigins = listOf(*value))
    override fun fromOrigins(normalWebs: Boolean?, installedWebs: Boolean?, extensions: Boolean?): NextConfigurationsExceptOriginType =
        copy(originFromNormalWebs = normalWebs, originFromInstalledWebs =  installedWebs, originFromExtensions = extensions)


}

interface TemporalConfiguration {
    fun from(timeInMillis: Double): NextConfigurationsExceptTemporal
}

interface HostConfiguration {
    fun whenHostNameMatches(vararg value: String): NextConfigurationsExceptHosts
}

interface OriginTypeConfigurations {
    fun fromAllOrigins(): NextConfigurationsExceptOriginType
    fun fromOrigins(normalWebs: Boolean? = null, installedWebs: Boolean? = null, extensions: Boolean? = null): NextConfigurationsExceptOriginType
}

interface ExcludeOriginsConfigurations {
    fun fromOriginsExcluding(vararg value: String): NextConfigurationsExceptExcludeOrigins
}

interface IncludeOriginsConfiguration {
    fun fromOriginsIncluding(vararg value: String): NextConfigurationsExceptIncludeOrigins
}

interface ConfigurationData {
    val data: Options
}

interface AllConfigurations : IncludeOriginsConfiguration, ExcludeOriginsConfigurations, OriginTypeConfigurations, TemporalConfiguration, HostConfiguration

interface NextConfigurationsExceptTemporal : ConfigurationData, HostConfiguration, OriginTypeConfigurations, IncludeOriginsConfiguration, ExcludeOriginsConfigurations

interface NextConfigurationsExceptHosts : ConfigurationData, OriginTypeConfigurations, IncludeOriginsConfiguration, ExcludeOriginsConfigurations

interface NextConfigurationsExceptOriginType: ConfigurationData, IncludeOriginsConfiguration, ExcludeOriginsConfigurations

interface NextConfigurationsExceptIncludeOrigins: ConfigurationData

interface NextConfigurationsExceptExcludeOrigins: ConfigurationData

