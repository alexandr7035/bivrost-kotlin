pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "swisseth"

include(":swisseth-solidity-abi-parser")
include(":swisseth-solidity-types")
include(":swisseth-solidity-types-generator")
include(":swisseth-solidity-types-plugin")
include(":swisseth-solidity-utils")
