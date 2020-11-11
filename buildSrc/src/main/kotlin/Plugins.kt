import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*

class ConfigPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.js")

            repositories {
                jcenter()
            }

            dependencies {
                "implementation"(kotlin("stdlib-js"))
            }

            kotlin.apply {
                js {
                    browser()
                }
            }
        }
    }
}

class PublishPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.jfrog.bintray")
            apply(plugin = "maven-publish")

            bintray.apply {
                user = project.findProperty("bintrayUser") as String? ?: System.getenv("BINTRAY_USER")
                key = project.findProperty("bintrayApiKey") as String? ?: System.getenv("BINTRAY_API_KEY")
                publish = true

                pkg = PackageConfig().apply {
                    setLicenses("Apache-2.0")
                    repo = rootProject.name
                    name = project.name
                    vcsUrl = "https://github.com/iroyo/weked.git"
                }

                setPublications("Weked")
            }

            publishing.apply {
                publications.create<MavenPublication>("Weked") {
                    groupId = "dev.iroyo.weked"
                    artifactId = project.name

                    from(components["kotlin"])
                }
            }
        }
    }
}