pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "bivrost-kotlin"

include(":bivrost-abi-parser")
include(":bivrost-gradle-plugin")
include(":bivrost-solidity-types")
include(":bivrost-solidity-types-generator")
include(":bivrost-utils")
//include(":sample:app")
