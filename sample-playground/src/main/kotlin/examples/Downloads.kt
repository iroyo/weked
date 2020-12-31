package examples

import downloads.download
import downloads.getDownloadsWhere


fun testDownload() {
    download("") {

    }
}

fun testSearchDownload() {
    getDownloadsWhere {
        id = 1
        limit = 10
    }.then {
        console.log(it.first())
    }
}