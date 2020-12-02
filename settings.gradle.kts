rootProject.name = "weked"

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

include("weked-common")
include("weked-firefox")
include("weked-chrome")
include("sample-playground")
