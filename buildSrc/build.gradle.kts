plugins {
    `kotlin-dsl`
}

gradlePlugin {
    //ConfigPlugin
    plugins {
        register("PublishPlugin") {
            id = "weked-publish"
            implementationClass = "PublishPlugin"
        }
    }
    plugins {
        register("ConfigPlugin") {
            id = "weked-config"
            implementationClass = "ConfigPlugin"
        }
    }
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.5")
}