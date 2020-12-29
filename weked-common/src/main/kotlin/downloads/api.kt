package downloads

import browser
import create
import isFirefox
import jsObject
import mapToArray

private val api = browser.downloads

class DownloadConfigurator internal constructor() {
    internal val options = create<DownloadOptions> { }

    private inline fun assignIfFirefox(block: DownloadOptions.() -> Unit) = if (isFirefox) assign(block) else Unit
    private inline fun assign(block: DownloadOptions.() -> Unit) = with(options, block)
    private fun createHeader(pair: Pair<String, String>) = create<Header> {
        this.name = pair.first
        this.value = pair.second
    }

    val showSaveAsDialog = assign { saveAs = true }
    val incognitoMode = assignIfFirefox { incognito = true }
    val allowHttpErrors = assignIfFirefox { allowHttpErrors = true }
    fun fileName(value: String) = assign { filename = value }
    fun resolutionStrategy(action: ConflictAction) = assign { conflictAction = action.name }

    fun headers(vararg header: Pair<String, String>) = assign {
        headers = header.mapToArray(::createHeader)
    }
}

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