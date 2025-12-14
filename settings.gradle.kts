pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "bivkmp"

include(":bivkmp-solidity-abi-parser")
include(":bivkmp-solidity-types")
include(":bivkmp-solidity-types-generator")
include(":bivkmp-solidity-types-plugin")
include(":bivkmp-solidity-utils")
