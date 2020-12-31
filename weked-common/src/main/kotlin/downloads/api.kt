package downloads

import SingleMapListener
import browser
import create
import downloads.models.DownloadEntry
import downloads.models.DownloadEntryDelta
import jsObject

private val api = browser.downloads

/**
 * Fires with the downloadId when a download is erased from history.
 */
val onDownloadErased = api.onErased

/**
 * Fires with the DownloadItem object when a download begins.
 */
val onDownloadCreated = SingleMapListener(api.onCreated, ::DownloadEntry)

/**
 * he onChanged() event of the downloads API is fired when any of a downloads DownloadItem's properties changes
 * * (except for bytesReceived)
 */
val onDownloadChanged = SingleMapListener(api.onChanged, ::DownloadEntryDelta)

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

/**
 * Get downloads that matches the criteria
 */
fun getDownloadsWhere(block: DownloadSearch.() -> Unit) = api.search(DownloadSearch.getQuery(block))
    .then { it.map(::DownloadEntry) }

/**
 * Retrieves an icon for the specified download.
 */
fun getDownloadFileIcon(id: Int, size: Int = 32) = api.getFileIcon(id, create { this.size = size })

/**
 * Erases matching DownloadItems from the browser's download history, without deleting the downloaded files from disk.
 */
fun eraseDownloadsWhere(block: DownloadSearch.() -> Unit) = api.erase(DownloadSearch.getQuery(block))

/**
 * Pauses a download.
 */
fun pauseDownload(id: Int) = api.pause(id)

/**
 * Resumes a paused download.
 */
fun resumeDownload(id: Int) = api.resume(id)

/**
 * Cancels a download.
 */
fun cancelDownload(id: Int) = api.cancel(id)

/**
 * Opens the downloaded file with its associated application.
 */
fun openDownload(id: Int) = api.open(id)

/**
 * Opens the platform's file manager application to show the downloaded file in its containing folder.
 */
fun showDownload(id: Int) = api.show(id)

/**
 *     Opens the platform's file manager application to show the default downloads folder.
 */
fun showDownloadDefaultFolder() = api.showDefaultFolder()

/**
 * Removes a downloaded file from disk, but not from the browser's download history.
 */
fun removeDownloadFile(id: Int) = api.removeFile(id)

/**
 * Prompts the user to accept or cancel a dangerous download.
 */
fun acceptDownloadDanger(id: Int) = api.acceptDanger(id)
