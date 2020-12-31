package downloads

import create
import downloads.models.ConflictAction
import isFirefox
import mapToArray

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