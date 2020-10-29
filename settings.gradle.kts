rootProject.name = "webext-kotlin-declarations"

pluginManagement {
    repositories {
        mavenCentral()
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-dev")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        gradlePluginPortal()
        mavenLocal()
    }
}

include("kotlin-webextensions")
include("sample")
