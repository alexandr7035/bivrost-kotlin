plugins {
    java
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":bivrost-utils"))
    implementation(project(":bivrost-solidity-types"))
    implementation(libs.kotlin.stdlib)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.kotlinpoet)
    implementation(libs.bouncycastle)
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

