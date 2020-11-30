plugins {
    id("org.jetbrains.kotlin.js")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(npm("webextension-polyfill", "0.7.0"))
    implementation(project(":weked-firefox"))
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")
}

kotlin {
    js {
        browser {
            distribution {
                directory = File("$projectDir/extension/output/")
                copy {
                    from("$rootDir/build/js/node_modules/webextension-polyfill/dist") {
                        include("browser-polyfill.min.js")
                    }
                    into("$projectDir/extension/output/")
                }
            }
            webpackTask {
                cssSupport.enabled = true
            }

            runTask {
                cssSupport.enabled = true
            }
        }
        binaries.executable()
    }
}
