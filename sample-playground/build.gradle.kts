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

val output = "$projectDir/extension/output/"
val npmPath = "$rootDir/build/js/node_modules"

kotlin {
    js {
        browser {
            distribution {
                directory = File(output)
                copy {
                    from("$npmPath/webextension-polyfill/dist") {
                        include("browser-polyfill.min.js")
                    }
                    into(output)
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

tasks["clean"].doFirst {
    delete(output)
}
