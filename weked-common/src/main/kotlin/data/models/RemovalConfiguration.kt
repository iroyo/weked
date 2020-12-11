package data.models

open class Options internal constructor(
    internal open val time: Double = 0.0,
    internal open val cookieStoreId: String? = null,
    internal open val hostNames: Array<String>? = null,
    internal open val excludingOrigins: Array<String>?  = null,
    internal open val includingOrigins: Array<String>?  = null,
)

data class RemovalConfiguration(
    override val time: Double = 0.0,
    override val cookieStoreId: String? = null,
    override val hostNames: Array<String>? = null,
    override val excludingOrigins: Array<String>? = null,
    override val includingOrigins: Array<String>? = null,
) : AllConfigurations,
    Options(),
    NextConfigurationsExceptHosts,
    NextConfigurationsExceptCookies,
    NextConfigurationsExceptTemporal,
    NextConfigurationsExceptIncludeOrigins,
    NextConfigurationsExceptExcludeOrigins {

    override val data: Options get() = this

    override fun from(timeInMillis: Double): NextConfigurationsExceptTemporal = copy(time = timeInMillis)
    override fun whenCookieIdIs(value: String): NextConfigurationsExceptCookies = copy(cookieStoreId = value)
    override fun whenHostNameMatches(vararg value: String): NextConfigurationsExceptHosts = copy(hostNames = arrayOf(*value))
    override fun fromOriginsIncluding(vararg value: String): NextConfigurationsExceptIncludeOrigins = copy(includingOrigins = arrayOf(*value))
    override fun fromOriginsExcluding(vararg value: String): NextConfigurationsExceptExcludeOrigins = copy(excludingOrigins = arrayOf(*value))
}

interface TemporalConfiguration {
    fun from(timeInMillis: Double): NextConfigurationsExceptTemporal
}

interface CookiesConfiguration {
    fun whenCookieIdIs(value: String): NextConfigurationsExceptCookies
}

interface HostConfiguration {
    fun whenHostNameMatches(vararg value: String): NextConfigurationsExceptHosts
}

interface ExcludeOriginsConfigurations {
    fun fromOriginsExcluding(vararg value: String): NextConfigurationsExceptExcludeOrigins
}

interface IncludeOriginsConfiguration {
    fun fromOriginsIncluding(vararg value: String): NextConfigurationsExceptIncludeOrigins
}

interface AllConfigurations :
    IncludeOriginsConfiguration,
    ExcludeOriginsConfigurations,
    TemporalConfiguration,
    HostConfiguration

interface ConfigurationData {
    val data: Options
}

interface NextConfigurationsExceptTemporal : ConfigurationData, CookiesConfiguration, HostConfiguration, IncludeOriginsConfiguration, ExcludeOriginsConfigurations

interface NextConfigurationsExceptCookies : ConfigurationData, HostConfiguration, IncludeOriginsConfiguration, ExcludeOriginsConfigurations

interface NextConfigurationsExceptHosts : ConfigurationData, IncludeOriginsConfiguration, ExcludeOriginsConfigurations

interface NextConfigurationsExceptIncludeOrigins: ConfigurationData

interface NextConfigurationsExceptExcludeOrigins: ConfigurationData

