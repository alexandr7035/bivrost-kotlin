plugins {
    java
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":swisseth-solidity-types"))
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinpoet)

    implementation(platform(libs.kotlincrypto.bom))
    implementation(libs.kotlincrypto.sha3)
    
    testImplementation(libs.kotlin.test)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            
            pom {
                description.set("Solidity utilities")
            }
        }
    }
}
