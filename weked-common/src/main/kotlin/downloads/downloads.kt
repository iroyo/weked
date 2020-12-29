package downloads

import kotlin.js.Promise

external class Downloads {
    fun download(options: DownloadOptions): Promise<Int>
    fun search()
    fun pause()
    fun resume()
    fun cancel()
    fun getFileIcon()
    fun open()
    fun show()
    fun showDefaultFolder()
    fun erase()
    fun removeFile()
    fun acceptDanger()
    fun drag()
    fun setSelfEnabled()
    
}