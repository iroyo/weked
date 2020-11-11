allprojects {
    //manage common setting and dependencies
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-dev")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

subprojects {
    group = "dev.iroyo.weked"
    version = "0.0.3-dev"
}