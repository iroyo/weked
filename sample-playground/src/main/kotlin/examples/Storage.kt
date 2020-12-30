package examples

import storage.clearAllData
import storage.getAllData
import storage.getDataWithKeys

fun testClearingStorage() {
    clearAllData.onLocalStorage.then {
        console.log("DONE")
    }
}

fun testGettingAllData() {
    getAllData.onLocalStorage.then {
        console.log(it)
    }
}

fun testingGettingData() {
    getDataWithKeys("test01", "test02").onLocalStorage.then {
        console.log(it)
    }
}