plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

fun env(name: String): String? = System.getenv(name)

val localProperties = java.util.Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

fun local(key: String): String? = localProperties.getProperty(key)

subprojects {
    group = "io.swisseth"
    version = env("LIBRARY_VERSION") ?: "0.1"

    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    plugins.withId("maven-publish") {
        val repo = local("github.repository")
        val user = local("github.user")
        val token = local("github.token")

        if (repo != null && user != null && token != null) {

            configure<PublishingExtension> {
                repositories {
                    mavenLocal()
                    maven {
                        name = "GitHubPackages"
                        url = uri("https://maven.pkg.github.com/$repo")
                        credentials {
                            username = user
                            password = token
                        }
                    }
                }

                publications.withType<MavenPublication> {
                    pom {
                        name.set(project.name)
                        description.set("SwissETH Kotlin Multiplatform")
                        url.set("https://github.com/$repo")
                    }
                }
            }

            tasks.withType<PublishToMavenRepository>().configureEach {
                enabled = true
            }
        } else {
            tasks.withType<PublishToMavenRepository>().configureEach {
                enabled = false
            }
        }
    }
}
