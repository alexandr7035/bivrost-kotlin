plugins {
    java
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(project(":swisseth-solidity-utils"))
    implementation(project(":swisseth-solidity-types"))
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.kotlinpoet)
    implementation(libs.bignum)

    implementation(platform(libs.kotlincrypto.bom))
    implementation(libs.kotlincrypto.sha3)

    testImplementation(libs.kotlin.test)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            
            pom {
                description.set("ABI parser")
            }
        }
    }
}
