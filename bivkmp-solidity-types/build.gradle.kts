plugins {
    `maven-publish`
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.bignum)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                name.set("bivkmp-solidity-types")
                description.set("Solidity types for Kotlin")
            }
        }
    }
    repositories {
        mavenLocal()
        maven {
            name = "repo"
            url = uri("../repo")
        }
    }
}
