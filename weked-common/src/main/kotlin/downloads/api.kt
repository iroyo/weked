package downloads

import browser
import downloads.models.DownloadConfigurator
import downloads.models.DownloadSearch
import jsObject

private val api = browser.downloads

private fun download(
    method: String,
    url: String,
    body: String? = null,
    block: DownloadConfigurator.() -> Unit
) = api.download(
    DownloadConfigurator().apply(block).options.apply {
        this.method = method
        this.url = url
        body?.let { this.body = it }
    }
)

/**
 * (GET) Downloads a file, given its URL and other optional preferences.
 */
fun download(
    url: String,
    block: DownloadConfigurator.() -> Unit
) = download("GET", url, block)

/**
 * (POST)  downloads a file, given its URL and other optional preferences.
 */
fun download(
    url: String,
    body: String,
    block: DownloadConfigurator.() -> Unit
) = download("POST", url, body, block)

/**
 * Get all downloads
 */
val getAllDownloads get() = api.search(jsObject())

fun getDownloadsWhere(block: DownloadSearch.() -> Unit) =
    api.search(DownloadSearch().apply(block).data)


