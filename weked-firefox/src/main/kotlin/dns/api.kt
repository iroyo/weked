package dns

import browser
import names

/**
 * Resolves the given hostname to a DNS record.
 */
fun dnsResolve(hostname: String, vararg flags: Flags) = browser.dns.resolve(hostname, flags.names)