package dns

import kotlin.js.Promise

external class Dns {
     fun resolve(hostname: String, flags: Array<String>? = definedExternally): Promise<DNSRecord>
}

external interface DNSRecord {
    val addresses: Array<String>
    val canonicalName: String?
    val isTRR: Boolean
}