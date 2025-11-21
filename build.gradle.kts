plugins {
    alias(libs.plugins.kotlin.jvm) apply false
}

fun getKey(name: String, defaultValue: String? = null): String {
    val value = System.getenv(name)
    return value ?: defaultValue ?: error("Environment variable $name is not set and no default value provided")
}

subprojects {
    group = "pm.gnosis"
    version = getKey("LIBRARY_VERSION", "0.0.42")

    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

