plugins {
    java
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(project(":bivrost-utils"))
    implementation(project(":bivrost-solidity-types"))
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.kotlinpoet)

    implementation(platform(libs.kotlincrypto.bom))
    implementation(libs.kotlincrypto.sha3)

    testImplementation(libs.junit)
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
    repositories {
        mavenLocal()
        maven {
            url = uri("../repo")
        }
    }
}

