plugins {
    id("org.jetbrains.kotlin.js")
}

version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")
    implementation(project(":kotlin-webextensions"))
}

kotlin {
    js {
        browser {
            distribution {
                directory = File("$projectDir/extension/output/")
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