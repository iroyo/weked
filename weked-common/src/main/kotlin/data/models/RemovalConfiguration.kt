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
    NoHostConfiguration,
    NoCookieConfiguration,
    NoTemporalConfiguration,
    NoIncludeOriginsConfiguration,
    NoExcludeOriginsConfiguration {

    override val data: Options get() = this

    override fun from(timeInMillis: Double): NoTemporalConfiguration = copy(time = timeInMillis)
    override fun whenCookieIdIs(value: String): NoCookieConfiguration = copy(cookieStoreId = value)
    override fun whenHostNameMatches(vararg value: String): NoHostConfiguration = copy(hostNames = arrayOf(*value))
    override fun fromOriginsIncluding(vararg value: String): NoIncludeOriginsConfiguration = copy(includingOrigins = arrayOf(*value))
    override fun fromOriginsExcluding(vararg value: String): NoExcludeOriginsConfiguration = copy(excludingOrigins = arrayOf(*value))
}

interface TemporalConfiguration {
    fun from(timeInMillis: Double): NoTemporalConfiguration
}

interface CookieConfiguration {
    fun whenCookieIdIs(value: String): NoCookieConfiguration
}

interface HostConfiguration {
    fun whenHostNameMatches(vararg value: String): NoHostConfiguration
}

interface ExcludeOriginsConfigurations {
    fun fromOriginsExcluding(vararg value: String): NoExcludeOriginsConfiguration
}

interface IncludeOriginsConfiguration {
    fun fromOriginsIncluding(vararg value: String): NoIncludeOriginsConfiguration
}

interface AllConfigurations :
    IncludeOriginsConfiguration,
    ExcludeOriginsConfigurations,
    TemporalConfiguration,
    HostConfiguration

interface ConfigurationData {
    val data: Options
}

interface NoTemporalConfiguration : ConfigurationData, CookieConfiguration, HostConfiguration, IncludeOriginsConfiguration, ExcludeOriginsConfigurations

interface NoCookieConfiguration : ConfigurationData, HostConfiguration, IncludeOriginsConfiguration, ExcludeOriginsConfigurations

interface NoHostConfiguration : ConfigurationData, IncludeOriginsConfiguration

interface NoIncludeOriginsConfiguration: ConfigurationData

interface NoExcludeOriginsConfiguration: ConfigurationData

