pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "bivkmp"

include(":bivkmp-abi-parser")
include(":bivkmp-gradle-plugin")
include(":bivkmp-solidity-types")
include(":bivkmp-solidity-types-generator")
include(":bivkmp-utils")
//include(":sample:app")
