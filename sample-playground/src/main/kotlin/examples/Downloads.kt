package examples

import downloads.download
import downloads.getDownloadsWhere


fun testDownload() {
    download("") {

    }
}

fun testSearchDownload() {
    val downloadsWhere = getDownloadsWhere {
        limit = 10
        exists = true
    }
    console.log(downloadsWhere.limit)
    console.log(downloadsWhere.exists)
    console.log(downloadsWhere.data.limit)
    console.log(downloadsWhere.data.exists)
}