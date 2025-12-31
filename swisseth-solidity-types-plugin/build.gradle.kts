plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

dependencies {
    implementation(project(":swisseth-solidity-abi-parser"))
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        create("solidityTypesPlugin") {
            id = "io.swisseth.solidity.types"
            implementationClass = "io.swisseth.solidity.plugin.SolidityTypesPlugin"
            displayName = "SwissETH Solidity Types Plugin"
            description = "Gradle plugin for generating Kotlin wrapper classes from Solidity ABI files"
        }
    }
}

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                description.set("Gradle plugin for Solidity ABI code generation")
            }
        }
    }
}
